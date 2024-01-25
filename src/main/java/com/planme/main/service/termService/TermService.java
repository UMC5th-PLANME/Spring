package com.planme.main.service.termService;

import com.planme.main.domain.Terms;
import com.planme.main.domain.mapping.TermsAgreement;
import com.planme.main.web.dto.TermDTO.TermRequestDTO;

import java.util.List;

public interface TermService {
    public List<TermsAgreement> agree(TermRequestDTO.TermAgreeDTO termAgreeDTO);
}
