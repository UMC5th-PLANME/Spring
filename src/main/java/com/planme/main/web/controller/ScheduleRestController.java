package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.converter.ScheduleConverter;
import com.planme.main.domain.Schedule;
import com.planme.main.service.scheduleService.ScheduleCommandService;
import com.planme.main.web.dto.ScheduleRequestDTO;
import com.planme.main.web.dto.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleRestController {

    private final ScheduleCommandService scheduleCommandService;

    @PostMapping
    public ApiResponse<ScheduleResponseDTO.CreateScheduleResultDTO> createSchedule(@RequestBody ScheduleRequestDTO.CreateScheduleDto request){
//        System.out.println("good");
        Schedule schedule = scheduleCommandService.createSchedule(request);

        return ApiResponse.onSuccess(ScheduleConverter.toCreateResultDTO(schedule));
    }
}
