package com.planme.main.service;


import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.FocusHandler;
import com.planme.main.apiPayload.exception.handler.MeStoryHandler;
import com.planme.main.converter.MeStoryConverter;
import com.planme.main.domain.Category;
import com.planme.main.domain.Focus;
import com.planme.main.domain.MeStoryFocus;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.FocusRepository;
import com.planme.main.repository.MeStoryFocusRepository;
import com.planme.main.web.dto.MeStoryRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeStoryCommandServiceImpl implements  MeStoryCommandService {

    private final MeStoryFocusRepository meStoryFocusRepository;
    private final CategoryRepository categoryRepository;
    private final FocusRepository focusRepository;

    @Override
    public Pair<MeStoryFocus,Integer> postMeStoryFocusByCategoryId(
            MeStoryRequestDTO.PostMeStoryFocusRequestDTO request, Long categoryId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime requestFocusTime = LocalTime.parse(request.getTotalFocusTime(), formatter);

        LocalDate currentDate = LocalDate.now();
        LocalDateTime startOfDay = LocalDateTime.of(currentDate, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(currentDate, LocalTime.MAX);
        Optional<MeStoryFocus> findMeStoryFocus = meStoryFocusRepository.findByCategoryIdAndUpdatedAtBetween(
                categoryId,startOfDay,endOfDay);
        Optional<Category> findCategory = categoryRepository.findById(categoryId);
        Optional<Focus> findFocus = Optional.ofNullable(focusRepository.findByCategoryId(categoryId));


        if (findMeStoryFocus.isPresent() && findFocus.isPresent()) {
            //update
            MeStoryFocus resultMeStoryFocus = findMeStoryFocus.get();

            LocalTime updatedTotalFocusTime = resultMeStoryFocus.getTotalFocusTime();

            LocalTime sumTime = updatedTotalFocusTime.plusHours(requestFocusTime.getHour()).
                                plusMinutes(requestFocusTime.getMinute());

            resultMeStoryFocus.setTotalFocusTime(sumTime);
            meStoryFocusRepository.save(resultMeStoryFocus);

            return Pair.of(resultMeStoryFocus, 1);

        } else if (findCategory.isPresent()) {
            //create, category 존재
            Category resultCategory = findCategory.get();

            if(findFocus.isPresent()) {
                Focus focus = findFocus.get();
                MeStoryFocus createMeStoryFocus = MeStoryConverter.toMeStoryFocus(request, resultCategory, focus);
                meStoryFocusRepository.save(createMeStoryFocus);

                return Pair.of(createMeStoryFocus, 2);
            }
            else{
                throw new MeStoryHandler(ErrorStatus.MESTORY_FOCUS_SETTING_NOT_FOUND);
            }

        } else {
            //Category not found
            throw new FocusHandler(ErrorStatus.CATEGORY_NOT_FOUND);
        }

    }


}
