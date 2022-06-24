package com.test_task.controller;
import java.util.ArrayList;
import java.util.List;

import com.test_task.model.Instrument;
import com.test_task.repository.InstrumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InstrumentController {

    private final WebSocket webSocket;

    private final InstrumentRepository instrumentRepository;

    private final InstrumentRepository quotesRepository;

    @GetMapping("/connect")
    public ResponseEntity<List<Instrument>> connectWebsockets() {
        try {
            webSocket.connect();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/instruments")
    public ResponseEntity<List<Instrument>> getAllInstruments(@RequestParam(required = false) String title) {
        try {
            List<Instrument> instruments = new ArrayList<Instrument>();
            if (title == null)
                instrumentRepository.findAll().forEach(instruments::add);
            if (instruments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(instruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Api Get Request for candlestickchart based on ISIN/description will be available here
    /*@GetMapping("/instruments/{id}")
    public ResponseEntity<Instrument> getInstrumentById(@PathVariable("id") long id) {
        Optional<Instrument> InstrumentData = instrumentRepository.findById(id);
        if (InstrumentData.isPresent()) {
            return new ResponseEntity<>(InstrumentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}


