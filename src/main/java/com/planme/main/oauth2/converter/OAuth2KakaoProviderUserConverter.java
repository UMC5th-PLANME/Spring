package com.planme.main.oauth2.converter;

import com.planme.main.oauth2.enums.OAuth2Config;
import com.planme.main.oauth2.user.GoogleUser;
import com.planme.main.oauth2.user.KakaoUser;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.oauth2.util.OAuth2Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public final class OAuth2KakaoProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if(!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())){
            return null;
        }
        return new KakaoUser(OAuth2Utils.getMainAttributes(
                providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
