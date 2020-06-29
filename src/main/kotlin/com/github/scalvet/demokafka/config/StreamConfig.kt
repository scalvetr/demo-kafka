package com.github.scalvet.demokafka.config

import com.github.scalvet.demokafka.stream.MessageStream
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Bean
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.SubscribableChannel

@EnableBinding(value = [MessageStream::class])
class StreamConfig {

    @Bean(name = ["clientMessageChannel"])
    fun clientMessageChannel(): SubscribableChannel {
        return DirectChannel()
    }
}