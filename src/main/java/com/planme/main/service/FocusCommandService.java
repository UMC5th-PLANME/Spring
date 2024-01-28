package com.planme.main.service;

import com.planme.main.domain.Focus;
import com.planme.main.web.dto.FocusRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface FocusCommandService {
    Pair<Focus,Integer> postFocusByCategoryId(FocusRequestDTO.PostFocusDTO request, Long categoryId);

}