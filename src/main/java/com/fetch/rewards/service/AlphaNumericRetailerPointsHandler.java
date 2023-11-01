package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

public class AlphaNumericRetailerPointsHandler implements PointsCalculator {

    private PointsCalculator pointsCalculator;
    @Override
    public void setNextPointsHandler(PointsCalculator pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }

    @Override
    public int processRecepit(Receipt receipt) {
        String alphaNumericRetailer = removeNonAlphanumeric(receipt.getRetailer());
        int pointsValue = alphaNumericRetailer.length();
        if (pointsCalculator != null) {
            int nextPointsValue = pointsCalculator.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private String removeNonAlphanumeric(String retailer) {
        retailer = retailer.replaceAll("[^a-zA-Z0-9]", "");
        return retailer;
    }
}
