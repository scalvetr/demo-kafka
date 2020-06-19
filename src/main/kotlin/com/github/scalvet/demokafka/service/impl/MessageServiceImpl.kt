package com.github.scalvet.demokafka.service.impl

import com.github.scalvet.demokafka.domain.model.Message
import com.github.scalvet.demokafka.infrastructure.KafkaSender
import com.github.scalvet.demokafka.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl @Autowired
constructor(val kafkaSender: KafkaSender) : MessageService {

    override fun send(message: Message) {
        kafkaSender.send(message)
    }
}