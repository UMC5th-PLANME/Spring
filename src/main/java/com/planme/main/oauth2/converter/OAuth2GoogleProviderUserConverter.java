package com.planme.main.oauth2.converter;

import com.planme.main.oauth2.enums.OAuth2Config;
import com.planme.main.oauth2.user.GoogleUser;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.oauth2.util.OAuth2Utils;

public final class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if(!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())){
            return null;
        }
        return new GoogleUser(OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
                );
    }
}
