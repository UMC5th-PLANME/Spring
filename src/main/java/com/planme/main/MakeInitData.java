package com.planme.main;

import com.planme.main.domain.Member;
import com.planme.main.domain.Category;
import com.planme.main.repository.MemberRepository;
import com.planme.main.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakeInitData {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void makeTestMemberAndCategory() {
        Member member1 = Member.builder()
                .nickname("testMember1")
                .email("test@test.com")
                .loginType(false)
                .status(true)
                .build();
        memberRepository.save(member1);

        Category category1 = Category.builder()
                .name("TestCategory1")
                .emoticon(":)")
                .color("Red")
                .meStoryHidden(false)
                .member(memberRepository.findByNickname("testMember1"))
                .build();
        categoryRepository.save(category1);
    }

}
