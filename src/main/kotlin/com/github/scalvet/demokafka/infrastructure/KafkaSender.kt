package com.github.scalvet.demokafka.infrastructure

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaSender
@Autowired constructor(val kafkaTemplate: KafkaTemplate<String, Message>) {
    var log = loggerFor(this::class.java)

    var kafkaTopic = "messages"

    fun send(message: Message) {
        kafkaTemplate.send(kafkaTopic, message.recipient, message)
    }
}