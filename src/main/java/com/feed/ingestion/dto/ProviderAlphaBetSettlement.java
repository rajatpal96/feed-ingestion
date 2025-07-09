package com.feed.ingestion.dto;

import lombok.Data;

@Data
public class ProviderAlphaBetSettlement {
    private String msg_type;
    private String event_id;
    private String outcome; // "1", "X", "2"
}