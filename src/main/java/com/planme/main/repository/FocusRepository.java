package com.planme.main.repository;

import com.planme.main.domain.Focus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FocusRepository extends JpaRepository<Focus, Long> {
    Focus findByCategoryId(Long categoryId);
}
