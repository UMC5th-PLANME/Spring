package com.planme.main;

import com.planme.main.domain.Member;
import com.planme.main.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakeInitData {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void makeTestMember() {
        Member member1 = Member.builder()
                .nickname("testMember1")
                .email("test@test.com")
                .loginType(false)
                .status(true)
                .build();
        memberRepository.save(member1);
    }
}
