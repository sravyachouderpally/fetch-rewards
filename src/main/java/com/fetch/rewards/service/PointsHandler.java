package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

import java.text.ParseException;

public interface PointsHandler {

    void setNextPointsHandler(PointsHandler nextPointsHandler);
    int processRecepit(Receipt receipt);
}
