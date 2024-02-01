package com.planme.main;

import com.planme.main.domain.Member;
import com.planme.main.domain.Category;
import com.planme.main.domain.Schedule;
import com.planme.main.domain.Terms;
import com.planme.main.domain.enums.Repeat;
import com.planme.main.repository.MemberRepository;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.ScheduleRepository;
import com.planme.main.repository.TermsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class MakeInitData {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ScheduleRepository scheduleRepository;
    private final TermsRepository termsRepository;

    @PostConstruct
    public void makeTestMemberAndCategoryAndSchedule() {
        Member member1 = Member.builder()
                .nickname("testMember1")
                .email("test@test.com")
                .loginType("false")
                .status(1)
                .build();
        memberRepository.save(member1);

        Category category1 = Category.builder()
                .name("TestCategory1")
                .emoticon(":)")
                .color(1)
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

        Terms serviceTerm = Terms.builder()
                .id(1L)
                .name("서비스 이용약관")
                .required(true)
                .build();

        Terms infoTerm = Terms.builder()
                .id(2L)
                .name("개인정보 수집 및 이용")
                .required(true)
                .build();

        termsRepository.save(serviceTerm);
        termsRepository.save(infoTerm);
    }

}
