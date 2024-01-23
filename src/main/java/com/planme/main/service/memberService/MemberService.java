package com.planme.main.service.memberService;

import com.planme.main.domain.Member;
import com.planme.main.oauth2.user.ProviderUser;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {
    public Member join(String registrationId, ProviderUser providerUser);
    public Member getMember(HttpServletRequest httpServletRequest);
}
