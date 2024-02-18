package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.AlarmConverter;
import com.planme.main.domain.Alarm;
import com.planme.main.service.alarmService.AlarmCommandService;
import com.planme.main.service.alarmService.AlarmQueryService;
import com.planme.main.web.dto.AlarmDTO.AlarmRequestDTO;
import com.planme.main.web.dto.AlarmDTO.AlarmResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Alarm")
public class AlarmRestController {

    private final AlarmCommandService alarmCommandService;
    private final AlarmQueryService alarmQueryService;

    @PostMapping
    public ApiResponse<AlarmResponseDTO.CreateAlarmResultDTO> createAlarm(HttpServletRequest httpServletRequest, @RequestBody AlarmRequestDTO.CreateAlarmDto request){
        Alarm alarm = alarmCommandService.createAlarm(httpServletRequest, request);
        return ApiResponse.onSuccess(AlarmConverter.toCreateResultDTO(alarm));
    }


    @GetMapping("/{schedule_id}")
    public ApiResponse<List<AlarmResponseDTO.GetAlarmResultDTO>> getAlarm(HttpServletRequest httpServletRequest, @PathVariable(name = "schedule_id") Long id){
        List<Alarm> alarmList = alarmQueryService.getAlarmList(httpServletRequest, id);
        return ApiResponse.of(SuccessStatus.ALARM_FOUND, AlarmConverter.toGetAlarmListDTO(alarmList));
    }

}
