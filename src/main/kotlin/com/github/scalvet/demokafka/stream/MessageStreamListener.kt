package com.github.scalvet.demokafka.stream

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


@Component
class MessageStreamListener {
    var log = loggerFor(this::class.java)

    @StreamListener(Processor.INPUT)
    fun listen(@Payload value: Message) {
        log.info("message received {}", value)
    }
}