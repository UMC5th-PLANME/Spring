package com.planme.main.service.scheduleService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.apiPayload.exception.handler.ScheduleHandler;
import com.planme.main.domain.Member;
import com.planme.main.domain.Schedule;
import com.planme.main.oauth2.TokenService;
import com.planme.main.repository.MemberRepository;
import com.planme.main.repository.ScheduleRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleQueryServiceImpl implements ScheduleQueryService{

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final TokenService tokenService;
    @Override
    public Schedule getSchedule(HttpServletRequest httpServletRequest, Long id){
        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Schedule schedule = scheduleRepository.findByIdAndCategory_Member_Id(id, member.getId()).orElseThrow(() -> new ScheduleHandler(ErrorStatus.SCHEDULE_NOT_FOUND));

        return schedule;
    }

    @Override
    public List<Schedule> getScheduleList(HttpServletRequest httpServletRequest){
        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<Schedule> scheduleList = scheduleRepository.findAllByCategory_Member_Id(member.getId());
        return scheduleList;
    }
}
