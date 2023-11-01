package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

public class RoundDollarPointsHandler implements PointsCalculator {
    private PointsCalculator pointsCalculator;

    private static final int ROUND_DOLLAR_POINTS = 50;
    @Override
    public void setNextPointsHandler(PointsCalculator pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }
    @Override
    public int processRecepit(Receipt receipt) {
        int pointsValue = 0;
        if(isRoundDollarAmount(receipt.getTotal())){
            pointsValue =  ROUND_DOLLAR_POINTS;
        }
        if (pointsCalculator != null) {
            int nextPointsValue = pointsCalculator.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private boolean isRoundDollarAmount(String inputStr) {
        if (inputStr.endsWith(".00")) {
            String dollarsStr = inputStr.split("\\.")[0];
            try {
                int dollars = Integer.parseInt(dollarsStr);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
