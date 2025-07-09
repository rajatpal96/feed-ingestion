package com.feed.ingestion.dto;

import lombok.Data;

@Data
public class ProviderBetaBetSettlement {
    private String type;
    private String event_id;
    private String result; // home, draw, away
}