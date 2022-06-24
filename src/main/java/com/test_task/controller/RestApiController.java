package com.test_task.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.test_task.model.Candle;
import com.test_task.model.Instrument;
import com.test_task.repository.InstrumentRepository;
import com.test_task.service.CandleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RestApiController {

    private final WebSocket webSocket;

    private final InstrumentRepository instrumentRepository;

    private final CandleService candleService;

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
    public ResponseEntity<List<Instrument>> getAllInstruments() {
        try {
            List<Instrument> instruments = new ArrayList<>();
            instrumentRepository.findAll().forEach(instruments::add);
            if (instruments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(instruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Api Get Request for candlestickchart based on ISIN
    @GetMapping("/instruments/{isin}")
    public ResponseEntity<String> displayCandlestickByIsin(@PathVariable("isin") String isin) {
        List<Instrument> instruments = instrumentRepository.findByIsin(isin);

        // There is no such ISIN in DB
        if (instruments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // ISIN is in DB, but is somehow not unique
        else if (instruments.size() > 1) {
            String report = "Given ISIN is not unique, displaying candlestick is not available.\n" +
                    "Connect PartnerService (http://localhost:8080) for further information.";
            return new ResponseEntity<>(report, HttpStatus.BAD_GATEWAY);

        // ISIN is in DB and is unique
        } else {
            Candle candle = candleService.create(isin);
            return new ResponseEntity<>(candle.toString(), HttpStatus.OK);
        }
    }
}


