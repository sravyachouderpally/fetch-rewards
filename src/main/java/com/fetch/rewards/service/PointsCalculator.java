package com.fetch.rewards.service;

import com.fetch.rewards.dto.Receipt;

public interface PointsCalculator {

    void setNextPointsHandler(PointsCalculator nextPointsCalculator);
    int processRecepit(Receipt receipt);
}
