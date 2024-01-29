package com.planme.main.service;

import com.planme.main.domain.Focus;
import com.planme.main.domain.MeStoryFocus;
import com.planme.main.web.dto.MeStoryRequestDTO;
import org.springframework.data.util.Pair;

public interface MeStoryCommandService {
    Pair<MeStoryFocus,Integer> postMeStoryFocusByCategoryId(
            MeStoryRequestDTO.PostMeStoryFocusRequestDTO requestForm, Long category_id);
}
