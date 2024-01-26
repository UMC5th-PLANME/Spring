package com.planme.main.service.scheduleService;

import com.planme.main.domain.Schedule;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ScheduleQueryService {
    Schedule getSchedule(HttpServletRequest httpServletRequest, Long id);

    List<Schedule> getScheduleList(HttpServletRequest httpServletRequest);

}
