package com.planme.main.service.scheduleService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.apiPayload.exception.handler.ScheduleHandler;
import com.planme.main.converter.ScheduleConverter;
import com.planme.main.domain.Category;
import com.planme.main.domain.Member;
import com.planme.main.domain.Schedule;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.MemberRepository;
import com.planme.main.repository.ScheduleRepository;
import com.planme.main.web.dto.ScheduleRequestDTO;
import com.planme.main.web.dto.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleCommandServiceImpl implements ScheduleCommandService{
    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    public Schedule createSchedule(ScheduleRequestDTO.CreateScheduleDto request){
        Long categoryId = request.getCategory_id();
        if (categoryId == null) {
            throw new IllegalArgumentException("카테고리 ID가 제공되지 않았습니다.");
        }
        Category category = categoryRepository.findById(request.getCategory_id()).orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));
        Schedule schedule = ScheduleConverter.toSchedule(request);
        schedule.setCategory(category);
        schedule.setStartDate(request.getStartDate());
        schedule.setEndDate(request.getEndDate());

        schedule = scheduleRepository.save(schedule);

        return schedule;
    }

    @Override
    @Transactional
    public ScheduleResponseDTO.DeleteScheduleResultDTO deleteSchedule(Long id){

        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Schedule schedule = scheduleRepository.findByIdAndCategory_Member_Id(id, member.getId()).orElseThrow(() -> new ScheduleHandler(ErrorStatus.SCHEDULE_NOT_FOUND));
        ScheduleResponseDTO.DeleteScheduleResultDTO deleteScheduleResultDTO = ScheduleConverter.toDeleteResultDTO(schedule);

        scheduleRepository.delete(schedule);

        return deleteScheduleResultDTO;
    }
}
