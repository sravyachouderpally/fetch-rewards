package com.fetch.rewards.config;

import com.fetch.rewards.dto.ReceiptInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ReceiptInfo databaseObject(){
        return new ReceiptInfo();
    }
}
