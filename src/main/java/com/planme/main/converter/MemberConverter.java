package com.planme.main.converter;

import com.planme.main.domain.Member;
import com.planme.main.web.dto.MemberDTO.MemberDTO;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public MemberDTO toMemberDTO(OAuth2User oAuth2User) {
        var attributes = oAuth2User.getAttributes();
        return MemberDTO.builder()
                .email((String)attributes.get("email"))
                .nickname((String)attributes.get("name"))
                .picture((String)attributes.get("picture"))
                .build();
    }

    public MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
