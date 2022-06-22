package com.test_task.mapper;

import com.test_task.dto.InstrumentDTO;
import com.test_task.model.Instrument;

public class InstrumentMapper {
    public static Instrument toEntity (InstrumentDTO instrumentDTO) {
        Instrument instrument = new Instrument(
                0,
                instrumentDTO.getData().getDescription(),
                instrumentDTO.getData().getIsin(),
                instrumentDTO.getType()
        );
        return instrument;
    }
}
