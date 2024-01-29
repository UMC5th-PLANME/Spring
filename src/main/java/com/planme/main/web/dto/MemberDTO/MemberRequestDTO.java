package com.planme.main.web.dto.MemberDTO;

import lombok.Getter;

public class MemberRequestDTO {
    @Getter
    public static class UpdateProfileDTO{
        private String name;
        private String image_url;
    }
}
