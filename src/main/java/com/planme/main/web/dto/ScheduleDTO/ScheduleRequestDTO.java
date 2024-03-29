package com.planme.main.web.dto.ScheduleDTO;

import com.planme.main.domain.enums.Repeat;
import lombok.Getter;

import java.time.LocalDate;

public class ScheduleRequestDTO {

    @Getter
    public static class CreateScheduleDto{
        private boolean status;
        private Long category_id;
        private Repeat repeat_period;
        private String title;
        private String start_time;
        private String end_time;
        private boolean alarm;
        private String alarm_time;
        private LocalDate startDate;  // 추가
        private LocalDate endDate;  // 추가
    }

    @Getter
    public static class UpdateScheduleDto{
        private boolean status;
        private Long category_id;
        private Repeat repeat_period;
        private String title;
        private String start_time;
        private String end_time;
        private boolean alarm;
        private String alarm_time;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
