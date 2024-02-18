package com.planme.main.service.alarmService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.domain.Alarm;
import com.planme.main.domain.Member;
import com.planme.main.oauth2.TokenService;
import com.planme.main.repository.AlarmRepository;
import com.planme.main.repository.MemberRepository;
import com.planme.main.repository.ScheduleRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmQueryServiceImpl implements AlarmQueryService{

    private final MemberRepository memberRepository;
    private final AlarmRepository alarmRepository;
    private final TokenService tokenService;

    @Override
    public List<Alarm> getAlarmList(HttpServletRequest httpServletRequest, Long id){
        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<Alarm> alarmList = alarmRepository.findAlarmsByScheduleId(id);

        return alarmList;
    }
}
