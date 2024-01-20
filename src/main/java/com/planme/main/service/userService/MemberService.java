package com.planme.main.service.userService;

import com.planme.main.domain.Member;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void register(String registrationId, ProviderUser providerUser){


        Member member = Member.builder()
                .loginType(registrationId)
                .nickname(providerUser.getUsername())
                .socialId(providerUser.getId())
                .email(providerUser.getEmail())
                .build();

        memberRepository.save(member);
    }
}
