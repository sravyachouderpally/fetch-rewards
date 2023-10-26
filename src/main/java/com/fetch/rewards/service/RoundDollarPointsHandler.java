package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;

public class RoundDollarPointsHandler implements PointsHandler{
    private PointsHandler pointsHandler;

    private static final int ROUND_DOLLAR_POINTS = 50;
    @Override
    public void setNextPointsHandler(PointsHandler pointsHandler) {
        this.pointsHandler = pointsHandler;
    }
    @Override
    public int processRecepit(Receipt receipt) {
        int pointsValue = 0;
        if(isRoundDollarAmount(receipt.getTotal())){
            pointsValue =  ROUND_DOLLAR_POINTS;
        }
        if (pointsHandler != null) {
            int nextPointsValue = pointsHandler.processRecepit(receipt);
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
