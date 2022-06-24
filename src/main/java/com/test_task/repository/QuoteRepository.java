package com.test_task.repository;

import com.test_task.model.Instrument;
import com.test_task.model.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {
    List<Quote> findByIsin(String isin);
}
