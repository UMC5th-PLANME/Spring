package com.planme.main.web.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MeStoryResponseDTO {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadMeStoryResultDTO{
        private Long category_id;
        private String category_name;
        private Long completed_num;
        private Long in_progress_num;
        private LocalTime focus_time;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadMeStoryResultListDTO{
        List<ReadMeStoryResultDTO> me_story_result;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostMeStoryResultDTO{
        private Long id;
        private Long categoryId;
        private String totalFocusTime;
        private String createdAt;
        private String updatedAt;

    }


}
