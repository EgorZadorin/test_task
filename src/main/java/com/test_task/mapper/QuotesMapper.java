package com.test_task.mapper;

import com.test_task.dto.QuoteDTO;
import com.test_task.model.Quote;

import java.sql.Timestamp;

public class QuotesMapper {

    public static Quote toEntity (QuoteDTO quoteDTO) {
        Quote quote = new Quote(
                0,
                quoteDTO.getData().getIsin(),
                quoteDTO.getData().getPrice(),
                new Timestamp(System.currentTimeMillis())
        );
        return quote;
    }
}
