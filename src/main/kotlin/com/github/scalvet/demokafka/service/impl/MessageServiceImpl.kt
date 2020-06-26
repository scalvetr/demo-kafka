package com.github.scalvet.demokafka.service.impl

import com.github.scalvet.demokafka.domain.model.Message
import com.github.scalvet.demokafka.service.MessageService
import com.github.scalvet.demokafka.stream.MessageStreamSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.util.function.Consumer
import javax.annotation.PostConstruct


@Service
class MessageServiceImpl @Autowired
constructor(val messageStreamSender: MessageStreamSender) : MessageService {
    //https://github.com/learning-spring-boot/learning-spring-boot-2nd-edition-code/blob/master/9/part1/chat/src/main/java/com/greglturnquist/learningspringboot/chat/OutboundChatService.java
    lateinit var publish: Flux<Message>
    lateinit var clientMessageSink: FluxSink<Message>

    @PostConstruct
    fun init() {
        publish = Flux.create<Message>({ sink ->
            this.clientMessageSink = sink
        }, FluxSink.OverflowStrategy.IGNORE).publish().autoConnect()
    }

    override fun send(message: Message) {
        messageStreamSender.send(message)
    }

    override fun recieved(message: Message) {
        clientMessageSink.next(message)
    }

    override fun subscribe(consumer: Consumer<Message>) {
        publish.subscribe(consumer)
    }
}