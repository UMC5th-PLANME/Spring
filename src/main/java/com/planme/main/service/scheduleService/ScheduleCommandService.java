package com.planme.main.service.scheduleService;

import com.planme.main.domain.Schedule;
import com.planme.main.web.dto.ScheduleRequestDTO;
import com.planme.main.web.dto.ScheduleResponseDTO;

public interface ScheduleCommandService {

    Schedule createSchedule(ScheduleRequestDTO.CreateScheduleDto request);

    ScheduleResponseDTO.DeleteScheduleResultDTO deleteSchedule(Long id);
}

