package com.planme.main.service.memberService;

import com.planme.main.domain.Member;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.web.dto.MemberDTO.MemberRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {
    public Member join(String registrationId, ProviderUser providerUser);
    public Member getMember(HttpServletRequest httpServletRequest);
    public Member findMember(Long id);
    Member updateMember(HttpServletRequest httpServletRequest, MemberRequestDTO.UpdateProfileDTO updateProfileDTO);
    Member deleteMember(HttpServletRequest httpServletRequest);
}
