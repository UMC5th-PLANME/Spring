package com.planme.main.service;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.FocusHandler;
import com.planme.main.domain.Focus;
import com.planme.main.repository.FocusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FocusQueryServiceImpl implements FocusQueryService {

    private final FocusRepository focusRepository;

    @Override
    public Focus getFocusByCategoryId(Long categoryId){
        Optional<Focus> optionalFocus = Optional.ofNullable(focusRepository.findByCategoryId(categoryId));

        return optionalFocus.orElseThrow(() -> new FocusHandler(ErrorStatus.CATEGORYID_NOT_FOUND));

    };
}
