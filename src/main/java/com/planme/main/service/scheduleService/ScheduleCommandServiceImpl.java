package com.planme.main.service.scheduleService;

import com.planme.main.converter.ScheduleConverter;
import com.planme.main.domain.Category;
import com.planme.main.domain.Schedule;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.ScheduleRepository;
import com.planme.main.web.dto.ScheduleRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleCommandServiceImpl implements ScheduleCommandService{
    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;
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
}
