package com.lemon.fuckoff.controllers;


import com.lemon.fuckoff.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageController {
    @Autowired
    MessageService messageService;

    /**
     * Retrieves a message telling to fuck off.
     */
    @GetMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getMessage(@RequestHeader(value = "X-User-Id") String userId) {
        log.info(String.format("Request received from '%s' userId", userId));
        return ResponseEntity.ok(messageService.getMessage());
    }
}
