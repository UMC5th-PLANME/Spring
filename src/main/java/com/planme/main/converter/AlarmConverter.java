package com.planme.main.converter;

import com.planme.main.domain.Alarm;
import com.planme.main.web.dto.AlarmDTO.AlarmResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AlarmConverter {

    public static AlarmResponseDTO.CreateAlarmResultDTO toCreateResultDTO(Alarm alarm){
        return AlarmResponseDTO.CreateAlarmResultDTO.builder()
                .id(alarm.getId())
                .schedule_id(alarm.getSchedule().getId())
                .build();
    }

    public static List<AlarmResponseDTO.GetAlarmResultDTO> toGetAlarmListDTO(List<Alarm> alarmList){
        return alarmList.stream()
                .map(AlarmConverter::toGetAlarmResultDTO)
                .collect(Collectors.toList());
    }

    private static AlarmResponseDTO.GetAlarmResultDTO toGetAlarmResultDTO(Alarm alarm) {
        return AlarmResponseDTO.GetAlarmResultDTO.builder()
                .id(alarm.getId())
                .schedule_id(alarm.getSchedule().getId())
                .build();
    }
}
