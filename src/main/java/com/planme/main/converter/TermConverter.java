package com.planme.main.converter;

import com.planme.main.domain.Member;
import com.planme.main.domain.Terms;
import com.planme.main.domain.mapping.TermsAgreement;
import com.planme.main.web.dto.TermDTO.TermRequestDTO;
import com.planme.main.web.dto.TermDTO.TermResponseDTO;

import java.util.List;
import java.util.stream.Collectors;


public class TermConverter {
    public static List<TermsAgreement> toAgreeTermsAgreement(List<Terms> termsList){
         return termsList.stream()
                .map(terms ->
                        TermsAgreement.builder()
                                .agree(true)
                                .terms(terms)
                                .build()
                ).collect(Collectors.toList());
    }

    public static List<TermsAgreement> toDisagreeTermsAgreement(List<Terms> termsList){
        return termsList.stream()
                .map(terms ->
                        TermsAgreement.builder()
                                .agree(false)
                                .terms(terms)
                                .build()
                ).collect(Collectors.toList());
    }

    public static TermResponseDTO.TermAgreeResultDTO toTermAgreeResultDTO(List<TermsAgreement> termsAgreementList){
        List<Long> agreeTermsId = termsAgreementList.stream()
                .filter(TermsAgreement::isAgree)
                .map(termsAgreement -> {
                    return termsAgreement.getTerms().getId();
                })
                .collect(Collectors.toList());
        List<Long> disagreeTermsId = termsAgreementList.stream()
                .filter(termsAgreement -> !termsAgreement.isAgree())
                .map(termsAgreement -> {
                    return termsAgreement.getTerms().getId();
                })
                .collect(Collectors.toList());
        Long memberId = termsAgreementList.get(0).getMember().getId();

        return TermResponseDTO.TermAgreeResultDTO.builder()
                .agreeTermIds(agreeTermsId)
                .disagreeTermIds(disagreeTermsId)
                .memberId(memberId)
                .build();
    }
}
