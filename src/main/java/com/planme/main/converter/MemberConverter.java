package com.planme.main.converter;

import com.planme.main.domain.Member;
import com.planme.main.oauth2.Token;
import com.planme.main.web.dto.MemberDTO.MemberDTO;
import com.planme.main.web.dto.MemberDTO.MemberResponseDTO;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public MemberDTO toMemberDTO(OAuth2User oAuth2User) {
        var attributes = oAuth2User.getAttributes();
        return MemberDTO.builder()
                .email((String)attributes.get("email"))
                .nickname((String)attributes.get("name"))
                .profile_image((String)attributes.get("picture"))
                .build();
    }

    public MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profile_image(member.getProfileImage())
                .build();
    }

    public MemberResponseDTO.UpdateProfileDTO toUpdateResultDTO(Member member){
        return MemberResponseDTO.UpdateProfileDTO.builder()
                .member_id(member.getId())
                .updated_at(member.getUpdatedAt())
                .image_url(member.getProfileImage())
                .name(member.getNickname())
                .build();
    }
    public MemberResponseDTO.getMemberDTO toGetMemberResultDTO(Member member){
        return MemberResponseDTO.getMemberDTO.builder()
                .member_id(member.getId())
                .nickname(member.getNickname())
                .profile_image(member.getProfileImage())
                .login_type(member.getLoginType())
                .email(member.getEmail())
                .created_at(member.getCreatedAt())
                .updated_at(member.getUpdatedAt())
                .status(member.getStatus())
                .build();
    }

    public MemberResponseDTO.DeleteMemberResultDTO toDeleteResultDTO(Member member) {
        return MemberResponseDTO.DeleteMemberResultDTO.builder()
                .member_id(member.getId())
                .deleted_at(member.getUpdatedAt())
                .build();
    }

    public MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member, Token token) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .member_id(member.getId())
                .created_at(member.getCreatedAt())
                .accessToken(token.getToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
