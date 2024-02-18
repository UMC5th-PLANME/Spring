package com.planme.main.service.alarmService;

import com.planme.main.domain.Alarm;
import com.planme.main.web.dto.AlarmDTO.AlarmRequestDTO;
import com.planme.main.web.dto.AlarmDTO.AlarmResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface AlarmCommandService {
    Alarm createAlarm(HttpServletRequest httpServletRequest, Long scheduleId);

    AlarmResponseDTO.DeleteAlarmResultDTO deleteAlarms(HttpServletRequest httpServletRequest, Long scheduleId);
}
