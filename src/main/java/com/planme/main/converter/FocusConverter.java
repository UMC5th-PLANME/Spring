package com.planme.main.converter;

import com.planme.main.domain.Category;
import com.planme.main.domain.Focus;
import com.planme.main.web.dto.FocusDTO.FocusRequestDTO;
import com.planme.main.web.dto.FocusDTO.FocusResponseDTO;

import java.time.LocalTime;

public class FocusConverter {

    public static FocusResponseDTO.ReadFocusResultDTO toReadFocusResultDTO(Focus focus) {
        return FocusResponseDTO.ReadFocusResultDTO.builder()
                .focusTime(focus.getFocusTime())
                .breakTime(focus.getBreakTime())
                .repeatCnt(focus.getRepeatCnt())
                .currentRepeatCnt(focus.getCurrentRepeatCnt())
                .createdAt(focus.getCreatedAt().toString())
                .updatedAt(focus.getUpdatedAt().toString())
                .build();
    }


    public static Focus toFocus(FocusRequestDTO.PostFocusDTO request, Category category) {
        return Focus.builder()
                .focusTime(LocalTime.parse(request.getFocusTime()))
                .breakTime(LocalTime.parse(request.getBreakTime()))
                .repeatCnt(request.getRepeatCnt())
                .currentRepeatCnt(0L)
                .category(category)
                .build();
    }

    public static FocusResponseDTO.PostFocusResultDTO toPostFocusResultDTO(Focus focus) {
        return FocusResponseDTO.PostFocusResultDTO.builder()
                .id(focus.getId())
                .categoryId(focus.getCategory().getId())
                .createdAt(focus.getCreatedAt().toString())
                .updatedAt(focus.getUpdatedAt().toString())
                .build();
    }


}
