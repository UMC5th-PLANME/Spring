package com.planme.main.web.dto.AlarmDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class AlarmResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateAlarmResultDTO{
        private Long id;
        private Long schedule_id;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAlarmResultDTO{
        private Long id;
        private Long schedule_id;
    }
}
