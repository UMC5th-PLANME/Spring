package com.planme.main.repository;

import com.planme.main.domain.MeStoryFocus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;


public interface MeStoryFocusRepository extends JpaRepository<MeStoryFocus, Long> {
    Optional<MeStoryFocus> findByCategoryIdAndUpdatedAtBetween(Long categoryId, LocalDateTime startOfDay, LocalDateTime endOfDay);

}
