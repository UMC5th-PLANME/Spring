package com.planme.main.web.dto.MemberDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberDTO {
    private String nickname;
    private String email;
    private String picture;

    @Builder
    public MemberDTO(String nickname, String email, String picture) {
        this.nickname = nickname;
        this.email = email;
        this.picture = picture;
    }
}
