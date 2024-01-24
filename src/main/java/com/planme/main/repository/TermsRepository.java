package com.planme.main.repository;

import com.planme.main.domain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms,Long> {
}
