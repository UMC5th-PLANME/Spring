package com.planme.main.service.memberService;

import com.planme.main.domain.Member;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Member join(String registrationId, ProviderUser providerUser){

        Member member = Member.builder()
                .loginType(registrationId)
                .nickname(providerUser.getUsername())
                .profileImage(providerUser.getPicture())
                .socialId(providerUser.getId())
                .email(providerUser.getEmail())
//                .authorities(providerUser.getAuthorities().stream().map(auth -> auth.toString()).collect(Collectors.toList()))
                .build();



        return memberRepository.save(member);
    }
}
