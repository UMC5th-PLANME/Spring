package com.planme.main.repository;

import com.planme.main.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Long countByCategoryIdAndStatus(Long category_id, boolean status);
}
