package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

public class PurchaseTimePointsHandler implements PointsCalculator {
    private static final int PURCHASE_TIME_POINTS = 10;
    private PointsCalculator pointsCalculator;
    @Override
    public void setNextPointsHandler(PointsCalculator pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }

    @Override
    public int processRecepit(Receipt receipt)  {
        int pointsValue = 0;
        if(checkPurchaseTimeRange(receipt.getPurchaseTime())){
            pointsValue = PURCHASE_TIME_POINTS;
        }
        if (pointsCalculator != null) {
            int nextPointsValue = pointsCalculator.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private boolean checkPurchaseTimeRange(String purchaseTime) {
        String[] timeParts = purchaseTime.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        if (hour >= 14 && hour < 16) {
            return true;
        }
        return false;
    }
}
