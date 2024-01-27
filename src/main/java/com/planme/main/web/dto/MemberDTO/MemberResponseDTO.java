package com.planme.main.web.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateProfileDTO{
        private Long member_id;
        private String name;
        private String image_url;
        private LocalDateTime updated_at;
    }
}
