package com.fetch.rewards.service;

import com.fetch.rewards.dto.Id;
import com.fetch.rewards.dto.Points;
import com.fetch.rewards.dto.Receipt;
import com.fetch.rewards.dto.ReceiptInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.UUID;

@Service
public class ReceiptProcessorService implements IReceiptProcessorService{

    private final ReceiptInfo receiptInfo;
    @Autowired
    public ReceiptProcessorService(ReceiptInfo receiptInfo){
        this.receiptInfo = receiptInfo;
    }
    @Override
    public Id processReceipt(Receipt receipt) {
        AlphaNumericRetailerPointsHandler alphaNumericRetailerPointsHandler = new AlphaNumericRetailerPointsHandler();
        RoundDollarPointsHandler roundDollarPointsHandler = new RoundDollarPointsHandler();
        QuarterTotalPointsHandler quarterTotalPointsHandler = new QuarterTotalPointsHandler();
        ItemCountPointsHandler itemCountPointsHandler = new ItemCountPointsHandler();
        ItemDescriptionPointsHandler itemDescriptionPointsHandler = new ItemDescriptionPointsHandler();
        PurchaseDatePointsHandler purchaseDatePointsHandler = new PurchaseDatePointsHandler();
        PurchaseTimePointsHandler purchaseTimePointsHandler = new PurchaseTimePointsHandler();

        alphaNumericRetailerPointsHandler.setNextPointsHandler(roundDollarPointsHandler);
        roundDollarPointsHandler.setNextPointsHandler(quarterTotalPointsHandler);
        quarterTotalPointsHandler.setNextPointsHandler(itemCountPointsHandler);
        itemCountPointsHandler.setNextPointsHandler(itemDescriptionPointsHandler);
        itemDescriptionPointsHandler.setNextPointsHandler(purchaseDatePointsHandler);
        purchaseDatePointsHandler.setNextPointsHandler(purchaseTimePointsHandler);

        int points = 0;
        points = alphaNumericRetailerPointsHandler.processRecepit(receipt);
        String receiptId = UUID.randomUUID().toString();
        receiptInfo.getReceiptToPoints().put(receiptId,points);
        Id id = new Id();
        id.setId(receiptId);
        return id;
    }

    @Override
    public Points getPoints(String id) {
        Points points = null;
        if(receiptInfo.getReceiptToPoints().containsKey(id)){
            points = new Points();
            int receiptPoints =  receiptInfo.getReceiptToPoints().get(id);
            points.setPoints(receiptPoints);
        }
        return points;
    }

}
