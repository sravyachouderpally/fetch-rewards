package com.fetch.rewards.service;

import com.fetch.rewards.dto.Id;
import com.fetch.rewards.dto.Points;
import com.fetch.rewards.dto.Receipt;
import com.fetch.rewards.dto.ReceiptInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptProcessorService implements IReceiptProcessorService{

    private final ReceiptInfo receiptInfo;
    private final AlphaNumericRetailerPointsHandler alphaNumericRetailerPointsHandler;
    @Autowired
    public ReceiptProcessorService(AlphaNumericRetailerPointsHandler alphaNumericRetailerPointsHandler,ReceiptInfo receiptInfo){
        this.receiptInfo = receiptInfo;
        this.alphaNumericRetailerPointsHandler = alphaNumericRetailerPointsHandler;
    }
    @Override
    public Id processReceipt(Receipt receipt) {
        int points = alphaNumericRetailerPointsHandler.processRecepit(receipt);
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
