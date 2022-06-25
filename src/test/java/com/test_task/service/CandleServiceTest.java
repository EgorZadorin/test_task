package com.test_task.service;

import com.test_task.model.Candle;
import com.test_task.model.Quote;
import com.test_task.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CandleServiceTest {

    CandleService candleService;

    @Mock
    QuoteRepository quoteRepository;

    @BeforeEach
    void setUp() {
        List<Quote> quotes = new ArrayList<>();
        quotes.add(new Quote(0, "TEST", 11, Timestamp.valueOf("2019-03-05 13:00:01")));
        quotes.add(new Quote(1, "TEST", 15, Timestamp.valueOf("2019-03-05 13:00:13")));
        quotes.add(new Quote(2, "TEST", 10, Timestamp.valueOf("2019-03-05 13:00:19")));
        quotes.add(new Quote(3, "TEST", 13, Timestamp.valueOf("2019-03-05 13:00:32")));
        quotes.add(new Quote(4, "TEST", 12, Timestamp.valueOf("2019-03-05 13:00:49")));
        quotes.add(new Quote(5, "TEST", 12, Timestamp.valueOf("2019-03-05 13:00:57")));
        Mockito.when(quoteRepository.findByIsin("TEST")).thenReturn(quotes);
        candleService = new CandleService(quoteRepository);
    }

    @Test
    @DisplayName("testing candlestick logic")
    void testCreate() {
        Candle testCandle = new Candle(Timestamp.valueOf("2019-03-05 13:00:01"),
                11, 15, 10, 12,
                Timestamp.valueOf("2019-03-05 13:00:57"));
        assertEquals(testCandle, candleService.create("TEST"));
    }
}