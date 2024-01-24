package com.planme.main.converter;

import com.planme.main.domain.Focus;
import com.planme.main.web.dto.FocusRequestDTO;
import com.planme.main.web.dto.FocusResponseDTO;

import java.time.LocalTime;

public class FocusConverter {

    public static FocusResponseDTO.ReadFocusResultDTO toReadFocusResultDTO(Focus focus){
        return FocusResponseDTO.ReadFocusResultDTO.builder()
                .focusTime(focus.getFocusTime())
                .breakTime(focus.getBreakTime())
                .repeatCnt(focus.getRepeatCnt())
                .currentRepeatCnt(focus.getCurrentRepeatCnt())
                .createdAt(focus.getCreatedAt().toString())
                .updatedAt(focus.getUpdatedAt().toString())
                .build();
    }
}
