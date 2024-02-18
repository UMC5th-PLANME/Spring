package com.planme.main.web.dto.AlarmDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AlarmRequestDTO {

    @Getter
    public static class CreateAlarmDto{
        private Long schedule_id;
    }
}
