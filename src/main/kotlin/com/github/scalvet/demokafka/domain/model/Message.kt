package com.github.scalvet.demokafka.domain.model

data class Message
(val recipient: String, val origin: String, val message: String)