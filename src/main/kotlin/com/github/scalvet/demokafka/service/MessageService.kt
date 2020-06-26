package com.github.scalvet.demokafka.service

import com.github.scalvet.demokafka.domain.model.Message
import java.util.function.Consumer

interface MessageService {
    fun send(message: Message)
    fun recieved(message: Message)
    fun subscribe(consumer: Consumer<Message>)
}