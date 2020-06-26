package com.github.scalvet.demokafka

import com.github.scalvet.demokafka.stream.ClientStream
import com.github.scalvet.demokafka.stream.MessageStream
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding

@SpringBootApplication
@EnableBinding(value = [MessageStream::class, ClientStream::class])
class DemoKafkaApplication

fun main(args: Array<String>) {
    runApplication<DemoKafkaApplication>(*args)
}