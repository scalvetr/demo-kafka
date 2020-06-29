package com.github.scalvet.demokafka.domain.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class Message
(
        val origin: String,
        val content: String,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) val timestamp: LocalDateTime
)