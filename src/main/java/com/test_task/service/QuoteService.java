package com.test_task.service;

import com.test_task.model.Quote;
import com.test_task.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public Quote create(Quote quote) {
        return quoteRepository.save(quote);
    }
}