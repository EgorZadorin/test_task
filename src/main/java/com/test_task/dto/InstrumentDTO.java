package com.test_task.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentDTO {
    InstrumentData data;
    String type;
}
