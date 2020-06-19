package com.github.scalvet.demokafka.infrastructure

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


@Component
class KafkaListener {
    var log = loggerFor(this::class.java)
    @KafkaListener(topics = ["messages"])
    fun listen(@Payload(required = false) value: Message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) key: String?) {
        log.info("message received {}", value)
    }
}