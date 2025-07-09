package com.feed.ingestion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandardizedMessage {
    private String type; // ODDS_CHANGE or BET_SETTLEMENT
    private String eventId;
    private Map<String, Double> odds; // home/draw/away
    private String result; // home/draw/away
}