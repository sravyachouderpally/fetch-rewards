package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;

public class AlphaNumericRetailerPointsHandler implements PointsHandler {

    private PointsHandler pointsHandler;
    @Override
    public void setNextPointsHandler(PointsHandler pointsHandler) {
        this.pointsHandler = pointsHandler;
    }

    @Override
    public int processRecepit(Receipt receipt) {
        String alphaNumericRetailer = removeNonAlphanumeric(receipt.getRetailer());
        int pointsValue = alphaNumericRetailer.length();
        if (pointsHandler != null) {
            int nextPointsValue = pointsHandler.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private String removeNonAlphanumeric(String retailer) {
        retailer = retailer.replaceAll("[^a-zA-Z0-9]", "");
        return retailer;
    }
}
