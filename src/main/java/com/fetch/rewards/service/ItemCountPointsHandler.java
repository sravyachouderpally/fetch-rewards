package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;

public class ItemCountPointsHandler implements PointsHandler{
    private static final int ITEM_COUNT_POINTS = 5;
    private PointsHandler pointsHandler;
    @Override
    public void setNextPointsHandler(PointsHandler pointsHandler) {
        this.pointsHandler = pointsHandler;
    }

    @Override
    public int processRecepit(Receipt receipt) {
        int pointsValue = 0;
        int evenItemCount = getEvenItemCount(receipt.getItems().size());
        pointsValue =  evenItemCount * ITEM_COUNT_POINTS;
        if (pointsHandler != null) {
            int nextPointsValue = pointsHandler.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private int getEvenItemCount(int itemCount) {
        return itemCount/2;
    }
}
