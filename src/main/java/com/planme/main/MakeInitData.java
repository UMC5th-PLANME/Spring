package com.planme.main;

import com.planme.main.domain.*;
import com.planme.main.domain.enums.Repeat;
import com.planme.main.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class MakeInitData {

    private final FocusRepository focusRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final MeStoryFocusRepository meStoryFocusRepository;
    private final ScheduleRepository scheduleRepository;

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

        Schedule schedule1 = Schedule.builder()
                .title("Test Schedule")
                .status(false)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .alarm(true)
                .alarmTime(LocalTime.of(9, 30))
                .repeatPeriod(Repeat.NONE)
                .repeatDetails("")
                .category(category1)
                .build();
        scheduleRepository.save(schedule1);

//        MeStoryFocus meStoryFocus1 = MeStoryFocus.builder()
//                .totalFocusTime(LocalTime.parse("10:00:00"))
//                .category(category1)
//                .build();
//        meStoryFocusRepository.save(meStoryFocus1);

        Focus focus1 = Focus.builder()
                .focusTime(LocalTime.parse("10:00:00"))
                .breakTime(LocalTime.parse("00:10:00"))
                .repeatCnt(2L)
                .currentRepeatCnt(0L)
                .category(categoryRepository.findByName("testCategory1"))
                .build();
        focusRepository.save(focus1);

    }
}


