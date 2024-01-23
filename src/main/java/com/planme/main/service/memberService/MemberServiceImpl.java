package com.planme.main.service.memberService;

import com.planme.main.domain.Member;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
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
                .build();

        //중복 회원 가입 방지
        if(memberRepository.existsBySocialId(member.getSocialId())){
            return null;
        }
        else{
            return memberRepository.save(member);
        }

    }
}
