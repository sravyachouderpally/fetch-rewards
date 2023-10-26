package com.fetch.rewards.dto;

import java.io.Serializable;
import java.util.HashMap;

public class ReceiptInfo implements Serializable {

    private HashMap<String, Integer> receiptToPoints;

    public ReceiptInfo() {
        this.receiptToPoints = new HashMap<>();
    }

    public HashMap<String, Integer> getReceiptToPoints() {
        return receiptToPoints;
    }

    public void setReceiptToPoints(HashMap<String, Integer> receiptToPoints) {
        this.receiptToPoints = receiptToPoints;
    }
}
