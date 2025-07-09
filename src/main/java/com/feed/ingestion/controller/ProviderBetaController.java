package com.feed.ingestion.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feed.ingestion.dto.ProviderBetaBetSettlement;
import com.feed.ingestion.dto.ProviderBetaOddsChange;
import com.feed.ingestion.service.FeedProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.feed.ingestion.constants.FeedConstants.MESSAGE_TYPE;
import static com.feed.ingestion.constants.FeedConstants.ODDS;

@RestController
@RequestMapping("/provider-beta/feed")
@RequiredArgsConstructor
public class ProviderBetaController {

    private final ObjectMapper mapper;
    private final FeedProcessorService processor;

    @PostMapping
    public ResponseEntity<?> handle(@RequestBody JsonNode body) {
        if (body.has(MESSAGE_TYPE) && ODDS.equals(body.get(MESSAGE_TYPE).asText())) {
            ProviderBetaOddsChange odds = mapper.convertValue(body, ProviderBetaOddsChange.class);
            processor.processBeta(odds);
        } else {
            ProviderBetaBetSettlement settlement = mapper.convertValue(body, ProviderBetaBetSettlement.class);
            processor.processBeta(settlement);
        }
        return ResponseEntity.ok().build();
    }
}