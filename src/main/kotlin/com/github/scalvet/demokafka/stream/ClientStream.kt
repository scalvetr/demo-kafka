package com.github.scalvet.demokafka.stream

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel


interface ClientStream {
    companion object {
        const val INPUT = "client-stream-input"
        const val OUTPUT = "client-stream-output"
    }

    @Input(INPUT)
    fun input(): SubscribableChannel

    @Output(OUTPUT)
    fun output(): MessageChannel
}