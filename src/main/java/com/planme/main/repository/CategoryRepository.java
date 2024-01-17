package com.planme.main.repository;

import com.planme.main.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByIdAndMemberId(Long categoryId, Long memberId);

}
