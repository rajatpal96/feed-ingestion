package com.feed.ingestion.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ProviderBetaOddsChange {
    private String type;
    private String event_id;
    private Map<String, Double> odds; // home, draw, away
}