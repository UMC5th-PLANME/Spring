package com.planme.main.service.memberService;

import com.planme.main.oauth2.converter.ProviderUserRequest;
import com.planme.main.oauth2.user.ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * 사용자가 OAuth 인증을 진행하고, 인증에 성공하면 OAuth2UserRequest 객체에 정보가 담겨져 넘어오게 된다.
 * 사용자 정보를 가져오는 역할을 담당
 */
@Service
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        // 기본 OAuth2UserService 객체 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        // 사용자 정보를 가져옴
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oAuth2User);

        ProviderUser providerUser = providerUser(providerUserRequest);
        // 회원 가입 하기
        super.register(providerUser, userRequest);

        return oAuth2User;
    }
}
