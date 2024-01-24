package com.planme.main.web.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

public class FocusResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadFocusResultDTO{

        private LocalTime focusTime;

        private LocalTime breakTime;

        private Long repeatCnt;

        private Long currentRepeatCnt;

        private String createdAt;

        private String updatedAt;
    }

}