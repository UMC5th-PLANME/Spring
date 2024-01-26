package com.planme.main.service.scheduleService;

import com.planme.main.domain.Schedule;
import com.planme.main.web.dto.ScheduleRequestDTO;
import com.planme.main.web.dto.ScheduleResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface ScheduleCommandService {

    Schedule createSchedule(HttpServletRequest httpServletRequest,ScheduleRequestDTO.CreateScheduleDto request);

    ScheduleResponseDTO.DeleteScheduleResultDTO deleteSchedule(HttpServletRequest httpServletRequest, Long id);
}

