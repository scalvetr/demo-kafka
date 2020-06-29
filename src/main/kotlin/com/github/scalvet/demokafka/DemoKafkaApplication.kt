package com.github.scalvet.demokafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoKafkaApplication

fun main(args: Array<String>) {
    runApplication<DemoKafkaApplication>(*args)
}