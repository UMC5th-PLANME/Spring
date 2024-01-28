package com.planme.main.converter;

import com.planme.main.web.dto.MeStoryResponseDTO;

import java.util.List;

public class MeStoryConverter {
    public static MeStoryResponseDTO.ReadMeStoryResultListDTO toGetFocusListDTO(
            List<MeStoryResponseDTO.ReadMeStoryResultDTO> meStoryResultList){
        return MeStoryResponseDTO.ReadMeStoryResultListDTO.builder()
                .me_story_result(meStoryResultList)
                .build();
    }

}
