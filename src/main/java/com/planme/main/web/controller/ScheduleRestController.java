package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.ScheduleConverter;
import com.planme.main.domain.Schedule;
import com.planme.main.service.scheduleService.ScheduleCommandService;
import com.planme.main.service.scheduleService.ScheduleQueryService;
import com.planme.main.web.dto.ScheduleRequestDTO;
import com.planme.main.web.dto.ScheduleResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleRestController {

    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleQueryService scheduleQueryService;
    @PostMapping
    public ApiResponse<ScheduleResponseDTO.CreateScheduleResultDTO> createSchedule(HttpServletRequest httpServletRequest, @RequestBody ScheduleRequestDTO.CreateScheduleDto request){
        Schedule schedule = scheduleCommandService.createSchedule(httpServletRequest, request);
        return ApiResponse.onSuccess(ScheduleConverter.toCreateResultDTO(schedule));
    }

    @GetMapping("/{schedule_id}")
    public ApiResponse<ScheduleResponseDTO.GetScheduleResultDTO> getSchedule(HttpServletRequest httpServletRequest, @PathVariable(name = "schedule_id") Long id){
        Schedule schedule = scheduleQueryService.getSchedule(httpServletRequest,id);
        return ApiResponse.of(SuccessStatus.SCHEDULE_FOUND, ScheduleConverter.toGetScheduleResultDTO(schedule));
    }

    @GetMapping
    public ApiResponse<ScheduleResponseDTO.GetScheduleListResultDTO> getScheduleList(HttpServletRequest httpServletRequest){
        List<Schedule> scheduleList = scheduleQueryService.getScheduleList(httpServletRequest);
        return ApiResponse.of(SuccessStatus.SCHEDULE_FOUND, ScheduleConverter.toGetScheduleListDTO(scheduleList));
    }

    @DeleteMapping("/{schedule_id}")
    public ApiResponse<ScheduleResponseDTO.DeleteScheduleResultDTO> deleteSchedule(HttpServletRequest httpServletRequest, @PathVariable(name = "schedule_id") Long id){
        return ApiResponse.of(SuccessStatus.SCHEDULE_DELETE, scheduleCommandService.deleteSchedule(httpServletRequest, id));
    }

    @PatchMapping("/{schedule_id}")
    public ApiResponse<ScheduleResponseDTO.UpdateScheduleResultDTO> updateSchedule(HttpServletRequest httpServletRequest, @PathVariable(name = "schedule_id") Long id, @RequestBody ScheduleRequestDTO.UpdateScheduleDto request){
        Schedule schedule = scheduleCommandService.updateSchedule(httpServletRequest, id, request);
        return ApiResponse.of(SuccessStatus.SCHEDULE_UPDATE, ScheduleConverter.toUpdateResultDTO(schedule));
    }
}
