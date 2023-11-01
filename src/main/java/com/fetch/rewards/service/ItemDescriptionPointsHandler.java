package com.fetch.rewards.service;

import com.fetch.rewards.dto.Item;
import com.fetch.rewards.dto.Receipt;

import java.util.List;

public class ItemDescriptionPointsHandler implements PointsCalculator {
    private PointsCalculator pointsCalculator;
    @Override
    public void setNextPointsHandler(PointsCalculator pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }

    @Override
    public int processRecepit(Receipt receipt) {
        int pointsValue = isMultipleOfThree(receipt.getItems());
        if (pointsCalculator != null) {
            int nextPointsValue = pointsCalculator.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private int isMultipleOfThree(List<Item> items) {
        int pointsValue = 0;
        for(Item item : items){
            if(item.getShortDescription().trim().length() % 3 ==0){
                double price = Double.parseDouble(item.getPrice());
                double priceValue = price * 0.2;
                pointsValue += (int) Math.ceil(priceValue);
            }
        }
        return pointsValue;
    }
}
