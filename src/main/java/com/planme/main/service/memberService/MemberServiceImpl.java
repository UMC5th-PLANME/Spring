package com.planme.main.service.memberService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.domain.Member;
import com.planme.main.oauth2.TokenService;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    @Transactional
    @Override
    public Member join(String registrationId, ProviderUser providerUser){

        Member member = Member.builder()
                .loginType(registrationId)
                .nickname(providerUser.getUsername())
                .profileImage(providerUser.getPicture())
                .socialId(providerUser.getId())
                .email(providerUser.getEmail())
                .build();

        //중복 회원 가입 방지
        if(memberRepository.existsBySocialId(member.getSocialId())){
            return null;
        }
        else{
            return memberRepository.save(member);
        }

    }

    @Override
    public Member getMember(HttpServletRequest httpServletRequest) {
        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        return memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
