package com.test_task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.test_task.dto.InstrumentDTO;
import com.test_task.dto.QuoteDTO;
import com.test_task.mapper.InstrumentMapper;
import com.test_task.mapper.QuotesMapper;
import com.test_task.model.Quote;
import com.test_task.service.InstrumentService;
import com.test_task.service.QuoteService;
import lombok.AllArgsConstructor;

import com.test_task.model.Instrument;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Component
public class WebSocket {

    private final InstrumentService instrumentService;

    private final QuoteService quoteService;

    private final WebSocketClient webSocketClient = new StandardWebSocketClient();

    public void connect() throws Exception {
        try {

            WebSocketSession webSocketSessionInstruments = webSocketClient.doHandshake(new TextWebSocketHandler() {
                @Override
                public void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {

                    String msg = message.getPayload();

                    Gson gson = new Gson();
                    InstrumentDTO instrumentDTO = gson.fromJson(msg, InstrumentDTO.class);
                    Instrument instrument = InstrumentMapper.toEntity(instrumentDTO);
                    instrumentService.create(instrument);
                }

                @Override
                public void afterConnectionEstablished(WebSocketSession session) {
                    System.out.println("established connection - " + session);
                }
            }, new WebSocketHttpHeaders(), URI.create("ws://localhost:8080/instruments")).get();

            WebSocketSession webSocketSessionQuotes = webSocketClient.doHandshake(new TextWebSocketHandler() {
                @Override
                public void handleTextMessage(WebSocketSession session, TextMessage message) {

                    String msg = message.getPayload();

                    Gson gson = new Gson();
                    QuoteDTO quoteDTO = gson.fromJson(msg, QuoteDTO.class);
                    Quote quote = QuotesMapper.toEntity(quoteDTO);
                    quoteService.create(quote);
                }

                @Override
                public void afterConnectionEstablished(WebSocketSession session) {
                    System.out.println("established connection - " + session);
                }
            }, new WebSocketHttpHeaders(), URI.create("ws://localhost:8080/quotes")).get();

            TimeUnit.SECONDS.sleep(30);
            webSocketSessionInstruments.close();
            webSocketSessionQuotes.close();

        } catch (Exception e) {
            System.out.println("Exception while accessing websockets");
            throw e;
        }
    }
}