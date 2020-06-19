package com.github.scalvet.demokafka.controller

import com.github.scalvet.demokafka.domain.model.Message
import com.github.scalvet.demokafka.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/messages")
@RestController
class MessagesController @Autowired constructor(val messageService: MessageService){

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun send(@RequestBody message: @Valid Message) {
        messageService.send(message);
    }
}