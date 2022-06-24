package com.test_task.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Candle {
    Timestamp openTimestamp;
    double openPrice;
    double highPrice;
    double lowPrice;
    double closePrice;
    Timestamp closeTimestamp;

    @Override
    public String toString() {
        return "openTimestamp: " + openTimestamp +
                "\nopenPrice: " + openPrice +
                "\nhighPrice: " + highPrice +
                "\nlowPrice: " + lowPrice +
                "\nclosePrice: " + closePrice +
                "\ncloseTimestamp: " + closeTimestamp;
    }
}
