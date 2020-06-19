package com.github.scalvet.demokafka.controller

import com.github.scalvet.demokafka.domain.model.Message
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/messages")
@RestController
class MessagesController {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody message: @Valid Message) {
    }
}