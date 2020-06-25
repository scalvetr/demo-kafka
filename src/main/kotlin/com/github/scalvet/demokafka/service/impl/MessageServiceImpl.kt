package com.github.scalvet.demokafka.service.impl

import com.github.scalvet.demokafka.domain.model.Message
import com.github.scalvet.demokafka.stream.MessageStreamSender
import com.github.scalvet.demokafka.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl @Autowired
constructor(val messageStreamSender: MessageStreamSender) : MessageService {

    override fun send(message: Message) {
        messageStreamSender.send(message)
    }
}