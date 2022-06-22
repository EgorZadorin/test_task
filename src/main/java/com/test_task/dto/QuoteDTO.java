package com.test_task.dto;

import com.test_task.model.QuoteData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDTO {
    QuoteData data;
    String type;
}
