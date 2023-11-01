package com.fetch.rewards.config;

import com.fetch.rewards.dto.ReceiptInfo;
import com.fetch.rewards.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ReceiptInfo databaseObject(){
        return new ReceiptInfo();
    }

    @Bean
    public AlphaNumericRetailerPointsHandler alphaNumericRetailerPointsHandler(){
        AlphaNumericRetailerPointsHandler alphaNumericRetailerPointsHandler = new AlphaNumericRetailerPointsHandler();
        RoundDollarPointsHandler roundDollarPointsHandler = new RoundDollarPointsHandler();
        QuarterTotalPointsHandler quarterTotalPointsHandler = new QuarterTotalPointsHandler();
        ItemCountPointsHandler itemCountPointsHandler = new ItemCountPointsHandler();
        ItemDescriptionPointsHandler itemDescriptionPointsHandler = new ItemDescriptionPointsHandler();
        PurchaseDatePointsHandler purchaseDatePointsHandler = new PurchaseDatePointsHandler();
        PurchaseTimePointsHandler purchaseTimePointsHandler = new PurchaseTimePointsHandler();

        alphaNumericRetailerPointsHandler.setNextPointsHandler(roundDollarPointsHandler);
        roundDollarPointsHandler.setNextPointsHandler(quarterTotalPointsHandler);
        quarterTotalPointsHandler.setNextPointsHandler(itemCountPointsHandler);
        itemCountPointsHandler.setNextPointsHandler(itemDescriptionPointsHandler);
        itemDescriptionPointsHandler.setNextPointsHandler(purchaseDatePointsHandler);
        purchaseDatePointsHandler.setNextPointsHandler(purchaseTimePointsHandler);
        return alphaNumericRetailerPointsHandler;
    }
}
