package com.planme.main.web.dto.ScheduleDTO;

import com.planme.main.domain.enums.Repeat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateScheduleResultDTO{
        private Long id;
        private boolean status;
        private Long category_id;
        private Repeat repeat_period;
        private String title;
        private String start_time;
        private String end_time;
        private boolean alarm;
        private String alarm_time;
        private String created_at;
        private String updated_at;
        private LocalDate startDate;  // 추가
        private LocalDate endDate;  // 추가
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetScheduleResultDTO{
        private Long id;
        private boolean status;
        private Long category_id;
        private Repeat repeat_period;
        private String title;
        private String start_time;
        private String end_time;
        private boolean alarm;
        private String alarm_time;
        private String created_at;
        private String updated_at;
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetScheduleListResultDTO{
        List<GetScheduleResultDTO> scheduleList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteScheduleResultDTO{
        Long schedule_id;
        LocalDateTime deletedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateScheduleResultDTO{
        private boolean status;
        private Long category_id;
        private Repeat repeat_period;
        private String title;
        private String start_time;
        private String end_time;
        private boolean alarm;
        private String alarm_time;
        private String created_at;
        private String updated_at;
        private LocalDate startDate;
        private LocalDate endDate;

    }
}
