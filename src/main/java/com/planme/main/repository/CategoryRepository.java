package com.planme.main.repository;

import com.planme.main.domain.Category;
import com.planme.main.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdAndMemberId(Long categoryId, Long memberId);
    List<Category> findAllByMember(Member member);
}
