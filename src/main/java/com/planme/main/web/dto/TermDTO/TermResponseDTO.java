package com.planme.main.web.dto.TermDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class TermResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermAgreeResultDTO{
        Long member_id;
        List<Long> agreeTermIds;
        List<Long> disagreeTermIds;
    }
}
