package com.github.scalvet.demokafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor

@SpringBootApplication
@EnableBinding(Processor::class)
class DemoKafkaApplication
fun main(args: Array<String>) {
    runApplication<DemoKafkaApplication>(*args)
}