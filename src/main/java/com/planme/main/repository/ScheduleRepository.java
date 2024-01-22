package com.planme.main.repository;

import com.planme.main.domain.Member;
import com.planme.main.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByIdAndCategory_Member_Id(Long id, Long memberId);

    List<Schedule> findAllByCategory_Member_Id(Long memberId);
}
