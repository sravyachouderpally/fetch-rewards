package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;

public class QuarterTotalPointsHandler implements PointsHandler{
    private static final int TOTAL_QUARTER_POINTS = 25;
    private PointsHandler pointsHandler;
    @Override
    public void setNextPointsHandler(PointsHandler pointsHandler) {
        this.pointsHandler = pointsHandler;
    }

    @Override
    public int processRecepit(Receipt receipt) {
        int pointsValue = 0;
        if(isMultipleOfQuarter(receipt.getTotal())){
            pointsValue =  TOTAL_QUARTER_POINTS;
        }
        if (pointsHandler != null) {
            int nextPointsValue = pointsHandler.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private boolean isMultipleOfQuarter(String total) {
        double totalAmount = Double.parseDouble(total);
        if (totalAmount % 0.25 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
