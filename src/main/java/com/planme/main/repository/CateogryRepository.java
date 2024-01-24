package com.planme.main.repository;

import com.planme.main.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CateogryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
