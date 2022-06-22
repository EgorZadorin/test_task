package com.test_task.service;

import com.test_task.model.Instrument;
import com.test_task.repository.InstrumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

// Сервис от компоненты ничем не отличается!
// DI IoC

@Service
@AllArgsConstructor
public class InstrumentService {
    private final InstrumentRepository instrumentRepository;

    public Instrument create(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }
}
