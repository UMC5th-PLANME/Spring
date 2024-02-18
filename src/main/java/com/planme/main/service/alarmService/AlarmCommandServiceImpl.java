package com.planme.main.service.alarmService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.apiPayload.exception.handler.ScheduleHandler;
import com.planme.main.domain.Alarm;
import com.planme.main.domain.Member;
import com.planme.main.domain.Schedule;
import com.planme.main.oauth2.TokenService;
import com.planme.main.repository.AlarmRepository;
import com.planme.main.repository.MemberRepository;
import com.planme.main.repository.ScheduleRepository;
import com.planme.main.web.dto.AlarmDTO.AlarmResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmCommandServiceImpl implements AlarmCommandService{

    private final ScheduleRepository scheduleRepository;
    private final TokenService tokenService;
    private final AlarmRepository alarmRepository;

    @Override
    public Alarm createAlarm(HttpServletRequest httpServletRequest, Long scheduleId){
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

    @Override
    @Transactional
    public AlarmResponseDTO.DeleteAlarmResultDTO deleteAlarms(HttpServletRequest httpServletRequest, Long schedule_id){
        List<Alarm> alarms = alarmRepository.findAlarmsByScheduleId(schedule_id);
        alarmRepository.deleteInBatch(alarms);
        return AlarmResponseDTO.DeleteAlarmResultDTO.builder()
                .schedule_id(schedule_id)
                .deleteAt(LocalDateTime.now())
                .build();
    }

}

