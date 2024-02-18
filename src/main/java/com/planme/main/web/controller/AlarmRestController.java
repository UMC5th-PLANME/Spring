package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.converter.AlarmConverter;
import com.planme.main.domain.Alarm;
import com.planme.main.service.alarmService.AlarmCommandService;
import com.planme.main.web.dto.AlarmDTO.AlarmRequestDTO;
import com.planme.main.web.dto.AlarmDTO.AlarmResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Alarm")
public class AlarmRestController {

    private final AlarmCommandService alarmCommandService;

    @PostMapping
    public ApiResponse<AlarmResponseDTO.CreateAlarmResultDTO> createAlarm(HttpServletRequest httpServletRequest, @RequestBody AlarmRequestDTO.CreateAlarmDto request){
        Alarm alarm = alarmCommandService.createAlarm(httpServletRequest, request);
        return ApiResponse.onSuccess(AlarmConverter.toCreateResultDTO(alarm));
    }
}
