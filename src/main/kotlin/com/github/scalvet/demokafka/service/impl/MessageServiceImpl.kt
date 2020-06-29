package com.github.scalvet.demokafka.service.impl

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import com.github.scalvet.demokafka.service.MessageService
import com.github.scalvet.demokafka.stream.MessageStreamSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.messaging.SubscribableChannel
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.util.function.Consumer
import javax.annotation.PostConstruct


@Service
class MessageServiceImpl @Autowired
constructor(
        val messageStreamSender: MessageStreamSender,
        @Qualifier("clientMessageChannel") val clientMessageChannel: SubscribableChannel
) : MessageService {
    var log = loggerFor(this::class.java)
    //https://github.com/learning-spring-boot/learning-spring-boot-2nd-edition-code/blob/master/9/part1/chat/src/main/java/com/greglturnquist/learningspringboot/chat/OutboundChatService.java
    lateinit var publish: Flux<Message>
    lateinit var clientMessageSink: FluxSink<Message>

    @PostConstruct
    fun init() {
        publish = Flux.create<Message>({ sink ->
            this.clientMessageSink = sink
        }, FluxSink.OverflowStrategy.IGNORE).publish().autoConnect()
        clientMessageChannel.subscribe{ handler ->
            received(handler.payload as Message)
        }
    }

    override fun send(message: Message) {
        log.info("send message {}", message)
        messageStreamSender.send(message)
    }

    override fun received(message: Message) {
        log.info("received message {}", message)
        clientMessageSink.next(message)
    }

    override fun subscribe(consumer: Consumer<Message>) {
        log.info("subscribing consumer")
        publish.subscribe(consumer)
    }
}