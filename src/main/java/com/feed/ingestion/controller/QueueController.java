package com.feed.ingestion.controller;

import com.feed.ingestion.dto.StandardizedMessage;
import com.feed.ingestion.queue.MessageQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Queue;

/*
this is controller which we will use to retrieve queued msg from memory just to check message processed and queued
 */
@RestController
@RequestMapping("/queue")
@RequiredArgsConstructor
public class QueueController {

    private final MessageQueue messageQueue;

    @GetMapping
    public ResponseEntity<?> handle() {
        Queue<StandardizedMessage> messages = messageQueue.getMessages();
        return ResponseEntity.ok().body(messages);
    }
}
