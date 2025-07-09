package com.feed.ingestion.service;

public interface FeedProcessorService {

    public void processAlpha(Object body);

    public void processBeta(Object body);
}
