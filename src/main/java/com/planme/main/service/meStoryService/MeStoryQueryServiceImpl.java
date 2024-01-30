package com.planme.main.service.meStoryService;


import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.MeStoryHandler;
import com.planme.main.domain.Category;
import com.planme.main.domain.MeStoryFocus;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.MeStoryFocusRepository;
import com.planme.main.repository.ScheduleRepository;
import com.planme.main.web.dto.MeStoryDTO.MeStoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MeStoryQueryServiceImpl implements MeStoryQueryService {

    private final MeStoryFocusRepository meStoryFocusRepository;
    private final CategoryRepository categoryRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<MeStoryResponseDTO.ReadMeStoryResultDTO> readMeStoryByMemberIdAndDate(Long member_id, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        List<Category> categories = categoryRepository.findByMemberId(member_id);

        if(!(date.length() == 8 && date.chars().allMatch(Character::isDigit))){
            throw new MeStoryHandler(ErrorStatus.MESTORY_REQUEST_FORM_ERROR);
        }
        else if (categories.isEmpty()) {
            throw new MeStoryHandler(ErrorStatus.MESTORY_MEMBER_NOT_FOUND);
        }

        List<MeStoryResponseDTO.ReadMeStoryResultDTO> resultDTOList = new ArrayList<>();
        for (Category category : categories) {
            Long inProgressCount = scheduleRepository.countByCategoryIdAndStatus(category.getId(), false);
            Long completedCount = scheduleRepository.countByCategoryIdAndStatus(category.getId(), true);

            LocalDate updatedAt = LocalDate.parse(date,formatter);
            LocalDateTime startOfDay = LocalDateTime.of(updatedAt, LocalTime.MIN);
            LocalDateTime endOfDay = LocalDateTime.of(updatedAt, LocalTime.MAX);
            Optional<MeStoryFocus> totalFocusTime = meStoryFocusRepository.findByCategoryIdAndUpdatedAtBetween(
                    category.getId(),startOfDay,endOfDay);

            MeStoryResponseDTO.ReadMeStoryResultDTO resultDTO = new MeStoryResponseDTO.ReadMeStoryResultDTO();
            resultDTO.setCategory_id(category.getId());
            resultDTO.setCategory_name(category.getName());
            resultDTO.setIn_progress_num(inProgressCount);
            resultDTO.setCompleted_num(completedCount);
            if(totalFocusTime.isPresent()){
                MeStoryFocus meStoryFocus = totalFocusTime.get();
                resultDTO.setFocus_time(meStoryFocus.getTotalFocusTime());
            }
            else{
                resultDTO.setFocus_time(LocalTime.of(0, 0, 0));
            }

            resultDTOList.add(resultDTO);
        }

        return resultDTOList;
    }
}
