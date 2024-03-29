package com.planme.main.converter;

import com.planme.main.domain.Schedule;
import com.planme.main.web.dto.ScheduleDTO.ScheduleRequestDTO;
import com.planme.main.web.dto.ScheduleDTO.ScheduleResponseDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleConverter {

    public static ScheduleResponseDTO.CreateScheduleResultDTO toCreateResultDTO(Schedule schedule){
        return ScheduleResponseDTO.CreateScheduleResultDTO.builder()
                .id(schedule.getId())
                .status(schedule.isStatus())
                .category_id(schedule.getCategory() != null ? schedule.getCategory().getId() : null)
                .repeat_period(schedule.getRepeatPeriod())
                .title(schedule.getTitle())
                .start_time(schedule.getStartTime().toString())
                .end_time(schedule.getEndTime().toString())
                .alarm(schedule.isAlarm())
                .alarm_time(schedule.getAlarmTime().toString())
                .created_at(schedule.getCreatedAt() != null ? schedule.getCreatedAt().toString() : null) // null 체크 추가
                .updated_at(schedule.getUpdatedAt() != null ? schedule.getUpdatedAt().toString() : null) // null 체크 추가
                .startDate(schedule.getStartDate())  // 추가
                .endDate(schedule.getEndDate())  // 추가
                .build();
    }

    public static Schedule toSchedule(ScheduleRequestDTO.CreateScheduleDto request){
        return Schedule.builder()
                .status(request.isStatus())
                .repeatPeriod(request.getRepeat_period())//RepeatPeriod로 수정
                .title(request.getTitle())
                .startTime(LocalTime.parse(request.getStart_time()))
                .endTime(LocalTime.parse(request.getEnd_time()))
                .alarm(request.isAlarm())
                .alarmTime(LocalTime.parse(request.getAlarm_time()))
                .build();
    }

    public static ScheduleResponseDTO.GetScheduleResultDTO toGetScheduleResultDTO(Schedule schedule){
        return ScheduleResponseDTO.GetScheduleResultDTO.builder()
                .id(schedule.getId())
                .status(schedule.isStatus())
                .category_id(schedule.getCategory() != null ? schedule.getCategory().getId() : null)
                .repeat_period(schedule.getRepeatPeriod())
                .title(schedule.getTitle())
                .start_time(schedule.getStartTime().toString())
                .end_time(schedule.getEndTime().toString())
                .alarm(schedule.isAlarm())
                .alarm_time(schedule.getAlarmTime().toString())
                .created_at(schedule.getCreatedAt() != null ? schedule.getCreatedAt().toString() : null)
                .updated_at(schedule.getUpdatedAt() != null ? schedule.getUpdatedAt().toString() : null)
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build();
    }

    public static ScheduleResponseDTO.GetScheduleListResultDTO toGetScheduleListDTO(List<Schedule> scheduleList){
        List<ScheduleResponseDTO.GetScheduleResultDTO> scheduleResultDTOList = scheduleList.stream()
                .map(ScheduleConverter::toGetScheduleResultDTO).collect(Collectors.toList());

        return ScheduleResponseDTO.GetScheduleListResultDTO.builder()
                .scheduleList(scheduleResultDTOList)
                .build();
    }

    public static ScheduleResponseDTO.DeleteScheduleResultDTO toDeleteResultDTO(Schedule schedule){
        return ScheduleResponseDTO.DeleteScheduleResultDTO.builder()
                .schedule_id(schedule.getId())
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static ScheduleResponseDTO.UpdateScheduleResultDTO toUpdateResultDTO(Schedule schedule){
        return ScheduleResponseDTO.UpdateScheduleResultDTO.builder()
                .status(schedule.isStatus())
                .category_id(schedule.getCategory() != null ? schedule.getCategory().getId() : null)
                .repeat_period(schedule.getRepeatPeriod())
                .title(schedule.getTitle())
                .start_time(schedule.getStartTime().toString())
                .end_time(schedule.getEndTime().toString())
                .alarm(schedule.isAlarm())
                .alarm_time(schedule.getAlarmTime().toString())
                .created_at(schedule.getCreatedAt().toString())
                .updated_at(schedule.getUpdatedAt().toString())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build();
    }

}
