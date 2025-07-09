package com.feed.ingestion.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feed.ingestion.dto.ProviderAlphaBetSettlement;
import com.feed.ingestion.dto.ProviderAlphaOddsChange;
import com.feed.ingestion.service.FeedProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.feed.ingestion.constants.FeedConstants.MESSAGE_TYPE;
import static com.feed.ingestion.constants.FeedConstants.ODDS_UPDATE;

@RestController
@RequestMapping("/provider-alpha/feed")
@RequiredArgsConstructor
public class ProviderAlphaController {

    private final ObjectMapper mapper;
    private final FeedProcessorService processor;

    @PostMapping
    public ResponseEntity<?> handle(@RequestBody JsonNode body) {
        if (body.has(MESSAGE_TYPE) && ODDS_UPDATE.equals(body.get(MESSAGE_TYPE).asText())) {
            ProviderAlphaOddsChange odds = mapper.convertValue(body, ProviderAlphaOddsChange.class);
            processor.processAlpha(odds);
        } else {
            ProviderAlphaBetSettlement settlement = mapper.convertValue(body, ProviderAlphaBetSettlement.class);
            processor.processAlpha(settlement);
        }
        return ResponseEntity.ok().build();
    }
}
