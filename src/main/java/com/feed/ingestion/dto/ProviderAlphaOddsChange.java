package com.feed.ingestion.dto;
import lombok.Data;
import java.util.Map;

@Data
public class ProviderAlphaOddsChange {
    private String msg_type;
    private String event_id;
    private Map<String, Double> values;
}