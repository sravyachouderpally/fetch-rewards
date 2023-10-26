package com.fetch.rewards.service;

import com.fetch.rewards.dto.Id;
import com.fetch.rewards.dto.Points;
import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;

public interface IReceiptProcessorService {

    Id processReceipt(Receipt receipt) throws ParseException;

    Points getPoints(String id);
}
