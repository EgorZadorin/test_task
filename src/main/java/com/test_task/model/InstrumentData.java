package com.test_task.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentData {
    public String description;
    public String isin;
}
