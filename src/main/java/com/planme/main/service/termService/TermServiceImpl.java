package com.planme.main.service.termService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.TermsHandler;
import com.planme.main.converter.TermConverter;
import com.planme.main.domain.Member;
import com.planme.main.domain.Terms;
import com.planme.main.domain.mapping.TermsAgreement;
import com.planme.main.repository.TermsAgreementRepository;
import com.planme.main.repository.TermsRepository;
import com.planme.main.service.memberService.MemberService;
import com.planme.main.web.dto.TermDTO.TermRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermServiceImpl implements TermService{
    private final MemberService memberService;
    private final TermsRepository termsRepository;
    private final TermsAgreementRepository termsAgreementRepository;

    @Override
    @Transactional
    public List<TermsAgreement> agree(TermRequestDTO.TermAgreeDTO termAgreeDTO) {
        Member member = memberService.findMember(termAgreeDTO.getMemberId());
        List<Terms> agreeTermsList = termAgreeDTO.getAgreeTermIds().stream()
                .map(termId -> {
                    return termsRepository.findById(termId).orElseThrow(() -> new TermsHandler(ErrorStatus.TERMS_NOT_FOUND));
                }).collect(Collectors.toList());
        List<Terms> disagreeTermsList = termAgreeDTO.getDisagreeTermIds().stream()
                .map(termId -> {
                    return termsRepository.findById(termId).orElseThrow(() -> new TermsHandler(ErrorStatus.TERMS_NOT_FOUND));
                }).collect(Collectors.toList());
        List<TermsAgreement> termsAgreementList = TermConverter.toAgreeTermsAgreement(agreeTermsList);
        List<TermsAgreement> termsDisagreementList = TermConverter.toDisagreeTermsAgreement(disagreeTermsList);
        List<TermsAgreement> allTermsAgreementList = new ArrayList<>(termsAgreementList);
        allTermsAgreementList.addAll(termsDisagreementList);
        allTermsAgreementList.forEach(termsAgreement -> {
            termsAgreement.setMember(member);
        });
        termsAgreementRepository.saveAll(allTermsAgreementList);

        return allTermsAgreementList;
    }

}
