package com.github.scalvet.demokafka.service

import com.github.scalvet.demokafka.domain.model.Message

interface MessageService {
    fun send(message: Message)
}