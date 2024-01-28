package com.planme.main.service;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.FocusHandler;
import com.planme.main.converter.FocusConverter;
import com.planme.main.domain.Category;
import com.planme.main.domain.Focus;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.FocusRepository;
import com.planme.main.web.dto.FocusRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FocusCommandServiceImpl implements FocusCommandService {

    private final FocusRepository focusRepository;
    private final CategoryRepository categoryRepository;

    public Pair<Focus,Integer> postFocusByCategoryId(FocusRequestDTO.PostFocusDTO request, Long categoryId) {
        Optional<Focus> findFocus = Optional.ofNullable(focusRepository.findByCategoryId(categoryId));
        Optional<Category> findCategory = categoryRepository.findById(categoryId);

        if (findFocus.isPresent()) {
            //update
            Focus resultFocus = findFocus.get();

            resultFocus.updateFocus(
                    LocalTime.parse(request.getFocusTime())
                    , LocalTime.parse(request.getBreakTime())
                    , request.getRepeatCnt());
            focusRepository.save(resultFocus);

            return Pair.of(resultFocus, 1);

        } else if (findCategory.isPresent()) {
            //create
            Category resultCategory = findCategory.get();

            Focus createFocus = FocusConverter.toFocus(request, resultCategory);
            focusRepository.save(createFocus);

            return Pair.of(createFocus, 2);
        } else {
            //Category not found
            throw new FocusHandler(ErrorStatus.CATEGORY_NOT_FOUND);
        }
    }
}
