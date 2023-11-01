package com.fetch.rewards.validator;

import com.fetch.rewards.dto.Item;
import com.fetch.rewards.dto.Receipt;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReceiptValidator {

    @Autowired
    private ItemValidator itemValidator;

    public void validateReceipt(Receipt receipt) throws Exception {
        if(receipt == null){
            throw new Exception("The receipt is invalid");
        }else {
            if (Strings.isBlank(receipt.getRetailer())) {
                throw new Exception("The receipt is invalid");
            }else if (Strings.isBlank(receipt.getPurchaseTime()) || !isTimeFormat(receipt.getPurchaseTime())) {
                throw new Exception("The receipt is invalid");
            }else if (Strings.isBlank(receipt.getPurchaseDate()) || !isDateFormat(receipt.getPurchaseDate())) {
                throw new Exception("The receipt is invalid");
            }else if (Strings.isBlank(receipt.getTotal()) || !isValidTotal(receipt.getTotal())) {
                throw new Exception("The receipt is invalid");
            }else if(receipt.getItems().isEmpty() || !isValidItem(receipt.getItems())){
                throw new Exception("The receipt is invalid");
            }
        }
    }

    private boolean isTimeFormat(String purchaseTime) {
        String pattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(purchaseTime);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    private boolean isDateFormat(String purchaseDate) {
        String pattern = "\\d{4}-\\d{2}-\\d{2}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(purchaseDate);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    private boolean isValidTotal(String total) {
        try {
            double value = Double.parseDouble(total);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidItem(List<Item> items) {
        if(!items.isEmpty()){
            for(Item item: items){
                if(!itemValidator.validateItem(item)){
                    return false;
                }
            }
        }
        return true;
    }

}
