package com.test_task.repository;

import java.util.List;

import com.test_task.model.Instrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends CrudRepository<Instrument, Long> {
    List<Instrument> findByIsin(String isin);
    void deleteByIsin(String isin);
}
