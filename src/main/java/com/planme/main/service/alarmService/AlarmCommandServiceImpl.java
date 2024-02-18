package com.planme.main.service.alarmService;

import com.planme.main.domain.Alarm;
import com.planme.main.domain.Member;
import com.planme.main.domain.Schedule;
import com.planme.main.oauth2.TokenService;
import com.planme.main.repository.AlarmRepository;
import com.planme.main.repository.ScheduleRepository;
import com.planme.main.web.dto.AlarmDTO.AlarmRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmCommandServiceImpl implements AlarmCommandService{

    private final ScheduleRepository scheduleRepository;
    private final TokenService tokenService;
    private final AlarmRepository alarmRepository;

    public Alarm createAlarm(HttpServletRequest httpServletRequest, AlarmRequestDTO.CreateAlarmDto request){
        Long scheduleId = request.getSchedule_id();
        if (scheduleId == null){
            throw new IllegalArgumentException("스케줄 ID가 제공되지 않았습니다.");
        }

        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 스케줄 ID를 가진 스케줄이 존재하지 않습니다."));
        Member member = schedule.getCategory().getMember();
        if (!member.getEmail().equals(email)) {
            throw new IllegalArgumentException("해당 스케줄에 대한 권한이 없습니다.");
        }

        Alarm alarm = Alarm.builder().schedule(schedule).build();

        schedule.addAlarm(alarm);
        alarmRepository.save(alarm);

        return alarm;
    }
}

