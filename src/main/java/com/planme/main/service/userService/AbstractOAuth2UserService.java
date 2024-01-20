package com.planme.main.service.userService;

import ch.qos.logback.core.net.server.Client;
import com.planme.main.domain.Member;
import com.planme.main.oauth2.user.GoogleUser;
import com.planme.main.oauth2.user.KakaoUser;
import com.planme.main.oauth2.user.OAuth2ProviderUser;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){
        //회원 있을 때 예외처리 필요
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        memberService.register(clientRegistration.getRegistrationId(),providerUser);
    }

    public ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User){

        String registrationId = clientRegistration.getRegistrationId();
        if(registrationId.equals("google")) {
            return new GoogleUser(oAuth2User, clientRegistration);

//        }else if(registrationId.equals("kakao")){
//            return new KakaoUser(oAuth2User,clientRegistration);
//        }
        }
        return null;
    }

}
