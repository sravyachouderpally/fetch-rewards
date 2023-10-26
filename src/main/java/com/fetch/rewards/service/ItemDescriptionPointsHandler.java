package com.fetch.rewards.service;

import com.fetch.rewards.dto.Item;
import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;
import java.util.List;

public class ItemDescriptionPointsHandler implements PointsHandler{
    private PointsHandler pointsHandler;
    @Override
    public void setNextPointsHandler(PointsHandler pointsHandler) {
        this.pointsHandler = pointsHandler;
    }

    @Override
    public int processRecepit(Receipt receipt) {
        int pointsValue = isMultipleOfThree(receipt.getItems());
        if (pointsHandler != null) {
            int nextPointsValue = pointsHandler.processRecepit(receipt);
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
