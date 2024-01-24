package com.planme.main;

import com.planme.main.domain.Focus;
import com.planme.main.domain.MeStoryFocus;
import com.planme.main.domain.Member;
import com.planme.main.domain.Category;
import com.planme.main.domain.enums.Repeat;
import com.planme.main.repository.CateogryRepository;
import com.planme.main.repository.FocusRepository;
import com.planme.main.repository.MeStoryFocusRepository;
import com.planme.main.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class MakeInitData {

    private final FocusRepository focusRepository;
    private final CateogryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final MeStoryFocusRepository meStoryFocusRepository;

    @PostConstruct
    public void makeTestFocus() {
        Member member1 = Member.builder()
                .nickname("testMember1")
                .email("test@test.com")
                .loginType(false)
                .status(true)
                .build();
        memberRepository.save(member1);

        Category category1 = Category.builder()
                .name("testCategory1")
                .emoticon(":)")
                .color("Red")
                .meStoryHidden(false)
                .member(memberRepository.findByNickname("testMember1"))
                .build();
        categoryRepository.save(category1);

        Focus focus1 = Focus.builder()
                .focusTime(LocalTime.parse("10:00:00"))
                .breakTime(LocalTime.parse("00:10:00"))
                .repeatCnt(2L)
                .currentRepeatCnt(2L)
                .category(categoryRepository.findByName("testCategory1"))
                .build();
        focusRepository.save(focus1);

    }
}


