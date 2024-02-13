package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.TokenConverter;
import com.planme.main.oauth2.Token;
import com.planme.main.oauth2.TokenService;
import com.planme.main.web.dto.TokenDTO.TokenResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    @GetMapping("/token")
    public OAuth2AccessToken token(@RegisteredOAuth2AuthorizedClient("google")OAuth2AuthorizedClient oAuth2AuthorizedClient){
        return oAuth2AuthorizedClient.getAccessToken();
    }


    @GetMapping("/token/expired")
    public ApiResponse<TokenResponseDTO.TokenRefreshDTO> refreshAuth(HttpServletRequest request) {
        String token = request.getHeader("Refresh");
        if (token != null && tokenService.verifyToken(token)) {
            String email = tokenService.getUid(token);
            Token newToken = tokenService.generateToken(email, "USER");

            return ApiResponse.of(SuccessStatus.TOKEN_REFRESHED, tokenConverter.toTokenRefreshDTO(newToken));
        }

        throw new RuntimeException("유효한 refresh 토큰이 아닙니다.");
    }


    @GetMapping("/token/test")
    public String test(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        String email = tokenService.getUid(token);
        System.out.println(email);
        return email;
    }
}
