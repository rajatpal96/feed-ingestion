package com.feed.ingestion.service.impl;

import com.feed.ingestion.dto.*;
import com.feed.ingestion.queue.MessageQueue;
import com.feed.ingestion.service.FeedProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Map;

import static com.feed.ingestion.constants.FeedConstants.*;

@Service
@Slf4j
public class FeedProcessorServiceImpl implements FeedProcessorService {

    private final MessageQueue messageQueue;

    public FeedProcessorServiceImpl(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    /*
      this method process feed from provider-alpha and push into in-memory queue
     */
    public void processAlpha(Object body) {
        if (body instanceof ProviderAlphaOddsChange msg && ODDS_UPDATE.equals(msg.getMsg_type())) {
            Map<String, Double> values = msg.getValues();
            StandardizedMessage normalized = StandardizedMessage.builder()
                    .type(ODDS_CHANGE)
                    .eventId(msg.getEvent_id())
                    .odds(Map.of(
                            HOME, values.get("1"),
                            DRAW, values.get("X"),
                            AWAY, values.get("2")
                    ))
                    .build();
            messageQueue.publish(normalized);
            log.info("[ALPHA] Normalized ODDS_CHANGE: {}", normalized);
        } else if (body instanceof ProviderAlphaBetSettlement msg && SETTLEMENT.equals(msg.getMsg_type())) {
            String result = switch (msg.getOutcome()) {
                case "1" -> HOME;
                case "X" -> DRAW;
                case "2" -> AWAY;
                default -> "unknown";
            };
            StandardizedMessage normalized = StandardizedMessage.builder()
                    .type(BET_SETTLEMENT)
                    .eventId(msg.getEvent_id())
                    .result(result)
                    .build();
            messageQueue.publish(normalized);
            log.info("[ALPHA] Normalized BET_SETTLEMENT: {}", normalized);
        }
    }

    /*
      this method process feed from provider-beta and push into in-memory queue
  */
    public void processBeta(Object body) {
        if (body instanceof ProviderBetaOddsChange msg && ODDS.equals(msg.getType())) {
            StandardizedMessage normalized = StandardizedMessage.builder()
                    .type(ODDS_CHANGE)
                    .eventId(msg.getEvent_id())
                    .odds(msg.getOdds())
                    .build();
            messageQueue.publish(normalized);
            log.info("[BETA] Normalized ODDS_CHANGE: {}", normalized);
        } else if (body instanceof ProviderBetaBetSettlement msg && SETTLEMENT.equalsIgnoreCase(msg.getType())) {
            StandardizedMessage normalized = StandardizedMessage.builder()
                    .type(BET_SETTLEMENT)
                    .eventId(msg.getEvent_id())
                    .result(msg.getResult())
                    .build();
            messageQueue.publish(normalized);
            log.info("[BETA] Normalized BET_SETTLEMENT: {}", normalized);
        }
    }
}
