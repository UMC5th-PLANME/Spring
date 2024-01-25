package com.planme.main.web.dto.MemberDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberDTO {
    private String nickname;
    private String email;
    private String profile_image;

    @Builder
    public MemberDTO(String nickname, String email, String profile_image) {
        this.nickname = nickname;
        this.email = email;
        this.profile_image = profile_image;
    }
}
