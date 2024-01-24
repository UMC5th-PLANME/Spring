package com.planme.main.service.memberService;

import com.planme.main.oauth2.converter.ProviderUserConverter;
import com.planme.main.oauth2.converter.ProviderUserRequest;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.repository.MemberRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {
    @Autowired
    private MemberServiceImpl memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    //회원 가입
    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){
        //회원 있을 때 예외처리 필요
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        memberService.join(clientRegistration.getRegistrationId(),providerUser);
    }

    // provider 유저객체 생성
    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){

        return providerUserConverter.converter(providerUserRequest);
    }

}
