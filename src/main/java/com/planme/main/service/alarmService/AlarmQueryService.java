package com.planme.main.service.alarmService;

import com.planme.main.domain.Alarm;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface AlarmQueryService {

    List<Alarm> getAlarmList(HttpServletRequest httpServletRequest, Long id);
}
