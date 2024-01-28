package com.planme.main.repository;

import com.planme.main.domain.MeStoryFocus;
import com.planme.main.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MeStoryFocusRepository extends JpaRepository<MeStoryFocus, Long> {
    Optional<MeStoryFocus> findByCategoryIdAndUpdatedAtBetween(Long categoryId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
