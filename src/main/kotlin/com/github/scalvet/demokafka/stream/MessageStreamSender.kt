package com.github.scalvet.demokafka.stream

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component


@Component
class MessageStreamSender {
    var log = loggerFor(this::class.java)

    @SendTo(Processor.OUTPUT)
    fun send(message: Message): Message {
        return message
    }
}