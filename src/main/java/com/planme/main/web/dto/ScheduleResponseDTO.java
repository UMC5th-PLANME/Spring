package com.planme.main.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ScheduleResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateScheduleResultDTO{
        private Long id;
        private boolean status;
        private Long category_id;
        private boolean repeat_status;
        private String title;
        private String start_time;
        private String end_time;
        private boolean alarm;
        private String alarm_time;
        private String created_at;
        private String updated_at;
    }
}
