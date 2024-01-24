package com.planme.main.service;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.FocusHandler;
import com.planme.main.domain.Focus;
import com.planme.main.repository.FocusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FocusCommandServiceImpl implements FocusCommandService {

    private final FocusRepository focusRepository;


}
