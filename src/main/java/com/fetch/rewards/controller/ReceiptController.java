package com.fetch.rewards.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.rewards.dto.Id;
import com.fetch.rewards.dto.Item;
import com.fetch.rewards.dto.Points;
import com.fetch.rewards.dto.Receipt;
import com.fetch.rewards.service.ReceiptProcessorService;
import com.fetch.rewards.validator.IdValidator;
import com.fetch.rewards.validator.ReceiptValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptProcessorService receiptProcessorService;
    @Autowired
    private ReceiptValidator receiptValidator;
    @Autowired
    private IdValidator idValidator;

    @PostMapping(value = "/process")
    public ResponseEntity<String> processReceipt(@RequestBody Receipt receipt) {
        try{
            receiptValidator.validateReceipt(receipt);
            Id id = receiptProcessorService.processReceipt(receipt);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}/points")
    public ResponseEntity<String> getPoints(@PathVariable String id) {
        try{
            if (!idValidator.isUUIDValid(id)) {
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


}
