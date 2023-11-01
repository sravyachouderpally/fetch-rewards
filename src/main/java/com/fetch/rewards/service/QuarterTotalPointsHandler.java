package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

public class QuarterTotalPointsHandler implements PointsCalculator {
    private static final int TOTAL_QUARTER_POINTS = 25;
    private PointsCalculator pointsCalculator;
    @Override
    public void setNextPointsHandler(PointsCalculator pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }

    @Override
    public int processRecepit(Receipt receipt) {
        int pointsValue = 0;
        if(isMultipleOfQuarter(receipt.getTotal())){
            pointsValue =  TOTAL_QUARTER_POINTS;
        }
        if (pointsCalculator != null) {
            int nextPointsValue = pointsCalculator.processRecepit(receipt);
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
