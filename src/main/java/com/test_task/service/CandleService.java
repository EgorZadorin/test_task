package com.test_task.service;

import com.test_task.model.Candle;
import com.test_task.model.Quote;
import com.test_task.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CandleService {

    private final QuoteRepository quoteRepository;

    public Candle create(String isin) {
        List<Quote> quotes = new ArrayList<>(quoteRepository.findByIsin(isin));
        int quotesSize = quotes.size();
        if (quotesSize != 0) {
            double lowPrice = quotes.get(0).getPrice();

            Candle candle = new Candle(quotes.get(0).getTs(), lowPrice , lowPrice, lowPrice,
                    quotes.get(quotesSize - 1).getPrice(), quotes.get(quotesSize - 1).getTs());

            double highPrice = lowPrice;
            for (int i = 1; i < quotesSize; ++i) {
                double price = quotes.get(i).getPrice();
                if (price <= highPrice) {
                    lowPrice = Math.min(lowPrice, price);
                } else {
                    highPrice = price;
                }
            }
            candle.setLowPrice(lowPrice);
            candle.setHighPrice(highPrice);
            return candle;
        } else {
            return new Candle();
        }
    }
}
