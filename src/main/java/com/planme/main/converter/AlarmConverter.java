package com.planme.main.converter;

import com.planme.main.domain.Alarm;
import com.planme.main.web.dto.AlarmDTO.AlarmResponseDTO;

public class AlarmConverter {

    public static AlarmResponseDTO.CreateAlarmResultDTO toCreateResultDTO(Alarm alarm){
        return AlarmResponseDTO.CreateAlarmResultDTO.builder()
                .id(alarm.getId())
                .schedule_id(alarm.getSchedule().getId())
                .build();
    }
}
