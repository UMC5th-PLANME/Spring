package com.planme.main.web.dto.TokenDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TokenResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenRefreshDTO {
        private String accessToken;
        private String refreshToken;
    }
}
