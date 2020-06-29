package com.github.scalvet.demokafka.controller

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import com.github.scalvet.demokafka.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.DirectProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxProcessor
import reactor.core.publisher.FluxSink
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.function.Consumer

@RequestMapping("/messages")
@RestController
class MessagesController @Autowired constructor(
        val messageService: MessageService
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

    @GetMapping(path = ["/stream"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun receive(): Flux<Message> {
        val processor: FluxProcessor<Message, Message> = DirectProcessor.create()
        val sink = processor.sink(FluxSink.OverflowStrategy.BUFFER)

        var handler = Consumer<Message> { message ->
            log.info("sending message to client {}", message)
            sink.next(message)
        }
        messageService.subscribe(handler)
        return processor.serialize().delayElements(Duration.of(2, ChronoUnit.SECONDS))
    }
}