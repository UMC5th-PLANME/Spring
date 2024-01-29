package com.planme.main.converter;

import com.planme.main.domain.Category;
import com.planme.main.domain.Focus;
import com.planme.main.domain.MeStoryFocus;
import com.planme.main.web.dto.FocusRequestDTO;
import com.planme.main.web.dto.FocusResponseDTO;
import com.planme.main.web.dto.MeStoryRequestDTO;
import com.planme.main.web.dto.MeStoryResponseDTO;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MeStoryConverter {
    public static MeStoryResponseDTO.ReadMeStoryResultListDTO toGetFocusListDTO(
            List<MeStoryResponseDTO.ReadMeStoryResultDTO> meStoryResultList){
        return MeStoryResponseDTO.ReadMeStoryResultListDTO.builder()
                .me_story_result(meStoryResultList)
                .build();
    }

    public static MeStoryFocus toMeStoryFocus(
            MeStoryRequestDTO.PostMeStoryFocusRequestDTO request, Category category, Focus focus) {
        focus.incrementCurrentRepeatCnt();

        return MeStoryFocus.builder()
                .totalFocusTime(LocalTime.parse(request.getTotalFocusTime()))
                .category(category)
                .build();
    }

    public static MeStoryResponseDTO.PostMeStoryResultDTO toPostMeStoryFocusResultDTO(MeStoryFocus meStoryFocus) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return MeStoryResponseDTO.PostMeStoryResultDTO.builder()
                .id(meStoryFocus.getId())
                .categoryId(meStoryFocus.getCategory().getId())
                .totalFocusTime(meStoryFocus.getTotalFocusTime().format(timeFormatter))
                .createdAt(meStoryFocus.getCreatedAt().toString())
                .updatedAt(meStoryFocus.getUpdatedAt().toString())
                .build();
    }

}
