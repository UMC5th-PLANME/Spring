package com.planme.main.repository;

import com.planme.main.domain.mapping.TermsAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsAgreementRepository extends JpaRepository<TermsAgreement,Long> {
}
