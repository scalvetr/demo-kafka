package com.github.scalvet.demokafka.controller

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import com.github.scalvet.demokafka.service.MessageService
import com.github.scalvet.demokafka.stream.ClientStream
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.messaging.MessageHandler
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RequestMapping("/messages")
@RestController
class MessagesController @Autowired constructor(
        val messageService: MessageService,
        val clientStream: ClientStream
) {
    var log = loggerFor(this::class.java)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    //fun send(@RequestBody messages: Flux<Message>) {
    //    messages.subscribe {
    //        log.info("send {}", it)
    //        messageService.send(it);
    //    }
    //}
    fun send(@RequestBody messages: Message) {
        log.info("send {}", messages)
        messageService.send(messages);
    }

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun receive(): Flux<Message> {
        return Flux.create { sink ->
            var handler = MessageHandler { message ->
                log.info("receive {}", message)
                sink.next(message.payload as Message)
            }
            clientStream.input().subscribe(handler)
            sink.onCancel { clientStream.input().unsubscribe(handler) }
        }
    }
}