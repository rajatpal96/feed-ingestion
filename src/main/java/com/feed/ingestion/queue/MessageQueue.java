package com.feed.ingestion.queue;

import com.feed.ingestion.dto.StandardizedMessage;

import java.util.Queue;

public interface MessageQueue {
    void publish(StandardizedMessage message);

    Queue<StandardizedMessage> getMessages();
}
