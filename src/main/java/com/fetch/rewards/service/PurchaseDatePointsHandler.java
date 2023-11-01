package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

public class PurchaseDatePointsHandler implements PointsCalculator {
    private PointsCalculator pointsCalculator;
    private static final int PURCHASE_DAY_ODD_POINTS = 6;
    @Override
    public void setNextPointsHandler(PointsCalculator pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }

    @Override
    public int processRecepit(Receipt receipt)  {
        int pointsValue = 0;
        if(isPurchaseDayOdd(receipt.getPurchaseDate())){
            pointsValue = PURCHASE_DAY_ODD_POINTS;
        }
        if (pointsCalculator != null) {
            int nextPointsValue = pointsCalculator.processRecepit(receipt);
            return pointsValue + nextPointsValue;
        }
        return pointsValue;
    }

    private boolean isPurchaseDayOdd(String purchaseDate) {
        String[] dateParts = purchaseDate.split("-");
        String dayPart = dateParts[2];
        int day = Integer.parseInt(dayPart);
        if (day % 2 != 0) {
            return true;
        }
        return false;
    }
}
