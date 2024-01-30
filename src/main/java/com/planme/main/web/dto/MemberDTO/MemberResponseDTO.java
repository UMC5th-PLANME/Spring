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
    public static class getMemberDTO{
        private Long member_id;
        private String nickname;
        private String profile_image;
        private String login_type;
        private String email;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        private Integer status;
    }
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
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteMemberResultDTO {
        private Long member_id;
        private LocalDateTime deleted_at;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        private Long member_id;
        private LocalDateTime created_at;
        private String accessToken;
        private String refreshToken;
    }
}
