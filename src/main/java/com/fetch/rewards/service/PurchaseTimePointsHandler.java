package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;
import java.time.LocalTime;

public class PurchaseTimePointsHandler implements PointsHandler{
    private static final int PURCHASE_TIME_POINTS = 10;
    private PointsHandler pointsHandler;
    @Override
    public void setNextPointsHandler(PointsHandler pointsHandler) {
        this.pointsHandler = pointsHandler;
    }

    @Override
    public int processRecepit(Receipt receipt)  {
        int pointsValue = 0;
        if(checkPurchaseTimeRange(receipt.getPurchaseTime())){
            pointsValue = PURCHASE_TIME_POINTS;
        }
        if (pointsHandler != null) {
            int nextPointsValue = pointsHandler.processRecepit(receipt);
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
