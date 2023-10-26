package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseDatePointsHandler implements PointsHandler{
    private PointsHandler pointsHandler;
    private static final int PURCHASE_DAY_ODD_POINTS = 6;
    @Override
    public void setNextPointsHandler(PointsHandler pointsHandler) {
        this.pointsHandler = pointsHandler;
    }

    @Override
    public int processRecepit(Receipt receipt)  {
        int pointsValue = 0;
        if(isPurchaseDayOdd(receipt.getPurchaseDate())){
            pointsValue = PURCHASE_DAY_ODD_POINTS;
        }
        if (pointsHandler != null) {
            int nextPointsValue = pointsHandler.processRecepit(receipt);
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
