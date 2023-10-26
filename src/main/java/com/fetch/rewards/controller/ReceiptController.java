package com.fetch.rewards.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.rewards.dto.Id;
import com.fetch.rewards.dto.Item;
import com.fetch.rewards.dto.Points;
import com.fetch.rewards.dto.Receipt;
import com.fetch.rewards.service.ReceiptProcessorService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptProcessorService receiptProcessorService;

    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    @PostMapping(value = "/process")
    public ResponseEntity<String> processReceipt(@RequestBody Receipt receipt) {
        try{
            validate(receipt);
            Id id = receiptProcessorService.processReceipt(receipt);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}/points")
    public ResponseEntity<String> getPoints(@PathVariable String id) {
        try{
            if (!isUUIDValid(id)) {
                throw new Exception("Id is invalid");
            }
            Points points = receiptProcessorService.getPoints(id);
            if(points!=null){
                return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(points));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No receipt found for that id");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private boolean isUUIDValid(String id) {
        return UUID_PATTERN.matcher(id).matches();
    }
    private void validate(Receipt receipt) throws Exception {
        if(receipt == null){
            throw new Exception("The receipt is invalid");
        }else {
            if (Strings.isBlank(receipt.getRetailer())) {
                throw new Exception("Invalid Retailer");
            }else if (Strings.isBlank(receipt.getPurchaseTime()) || !isTimeFormat(receipt.getPurchaseTime())) {
                throw new Exception("Invalid Purchase Time");
            }else if (Strings.isBlank(receipt.getPurchaseDate()) || !isDateFormat(receipt.getPurchaseDate())) {
                throw new Exception("Invalid Purchase Date");
            }else if (Strings.isBlank(receipt.getTotal()) || !isValidTotal(receipt.getTotal())) {
                throw new Exception("Invalid Receipt total");
            }else if(receipt.getItems().isEmpty() || !isValidItem(receipt.getItems())){
                throw new Exception("Invalid items");
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
                if(Strings.isBlank(item.getPrice()) || Strings.isBlank(item.getShortDescription())){
                    return false;
                }
            }
        }
        return true;
    }

}
