package com.planme.main.web.dto.MemberDTO;

import lombok.Getter;

public class MemberRequestDTO {
    @Getter
    public static class UpdateProfileDTO{
        private String name;
        private String image_url;
    }

    @Getter
    public static class JoinMemberDTO {
        private String name;
        private String profile_image;
        private String login_type;
        private String email;
    }
}
