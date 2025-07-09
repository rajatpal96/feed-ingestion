package com.feed.ingestion;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Feed Normalizer API", version = "1.0", description = "Standardizes provider feed messages"))
public class FeedIngestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedIngestionApplication.class, args);
    }

}
