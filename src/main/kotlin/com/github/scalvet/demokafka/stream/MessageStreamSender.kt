package com.github.scalvet.demokafka.stream

import com.github.scalvet.demokafka.config.loggerFor
import com.github.scalvet.demokafka.domain.model.Message
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component


@Component
class MessageStreamSender constructor(val source: Source) {
    var log = loggerFor(this::class.java)

    fun send(message: Message) {
        log.info("sending message to kafka {}", message)
        source.output().send(MessageBuilder.withPayload(message).build());
    }
}