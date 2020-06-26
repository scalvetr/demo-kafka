package com.github.scalvet.demokafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.cloud.stream.messaging.Source

@SpringBootApplication
@EnableBinding(value = [Source::class, Sink::class])
class DemoKafkaApplication

fun main(args: Array<String>) {
    runApplication<DemoKafkaApplication>(*args)
}