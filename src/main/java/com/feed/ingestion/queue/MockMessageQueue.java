package com.feed.ingestion.queue;


import com.feed.ingestion.dto.StandardizedMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
@Slf4j
public class MockMessageQueue implements MessageQueue {

    private final Queue<StandardizedMessage> queue = new LinkedList<>();

    @Override
    public void publish(StandardizedMessage message) {
        queue.add(message);
        log.info("[QUEUE] Published message to mock queue: {}", message);
    }

    public Queue<StandardizedMessage> getMessages() {
        return queue;
    }
}
