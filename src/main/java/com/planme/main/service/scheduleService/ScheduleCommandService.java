package com.planme.main.service.scheduleService;

import com.planme.main.domain.Schedule;
import com.planme.main.web.dto.ScheduleRequestDTO;

public interface ScheduleCommandService {

    Schedule createSchedule(ScheduleRequestDTO.CreateScheduleDto request);
}

