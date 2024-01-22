package com.planme.main.service.scheduleService;

import com.planme.main.domain.Schedule;

import java.util.List;

public interface ScheduleQueryService {
    Schedule getSchedule(Long id);

    List<Schedule> getScheduleList();

}
