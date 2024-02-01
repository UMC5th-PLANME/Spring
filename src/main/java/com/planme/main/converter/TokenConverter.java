package com.planme.main.converter;

import com.planme.main.oauth2.Token;
import com.planme.main.web.dto.TokenDTO.TokenResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TokenConverter {

    public  TokenResponseDTO.TokenRefreshDTO toTokenRefreshDTO(Token token) {
        return TokenResponseDTO.TokenRefreshDTO.builder()
                .accessToken(token.getToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
