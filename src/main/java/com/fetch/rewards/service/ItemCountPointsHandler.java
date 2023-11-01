package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

public class ItemCountPointsHandler implements PointsCalculator {
    private static final int ITEM_COUNT_POINTS = 5;
    private PointsCalculator pointsCalculator;
    @Override
    public void setNextPointsHandler(PointsCalculator pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }

    @Override
    public int processRecepit(Receipt receipt) {
        int pointsValue = 0;
        int evenItemCount = getEvenItemCount(receipt.getItems().size());
        pointsValue =  evenItemCount * ITEM_COUNT_POINTS;
        if (pointsCalculator != null) {
            int nextPointsValue = pointsCalculator.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private int getEvenItemCount(int itemCount) {
        return itemCount/2;
    }
}
