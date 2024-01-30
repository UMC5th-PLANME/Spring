package com.planme.main.service.focusService;

import com.planme.main.domain.Focus;
import com.planme.main.web.dto.FocusDTO.FocusRequestDTO;
import org.springframework.data.util.Pair;


public interface FocusCommandService {
    Pair<Focus,Integer> postFocusByCategoryId(FocusRequestDTO.PostFocusDTO request, Long categoryId);

}