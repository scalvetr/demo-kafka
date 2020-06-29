package com.github.scalvet.demokafka.stream

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import com.github.scalvet.demokafka.service.MessageService
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component


@Component
class MessageStreamListener {
    var log = loggerFor(this::class.java)

    @StreamListener(MessageStream.INPUT)
    @SendTo("clientMessageChannel")
    fun listen(@Payload message: Message) : Message {
        log.info("message received from kafka {}", message)
        return message
    }
}