package com.planme.main.web.dto.MeStoryDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MeStoryRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostMeStoryFocusRequestDTO{
        private String totalFocusTime;
    }


}
