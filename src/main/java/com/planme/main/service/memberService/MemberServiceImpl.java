package com.planme.main.service.memberService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.domain.Member;
import com.planme.main.oauth2.TokenService;
import com.planme.main.oauth2.user.ProviderUser;
import com.planme.main.repository.MemberRepository;
import com.planme.main.web.dto.MemberDTO.MemberRequestDTO;
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

    /**
     * oAuth2에 사용
     * @param registrationId
     * @param providerUser
     * @return
     */
    @Transactional
    @Override
    public Member join(String registrationId, ProviderUser providerUser){

        Member member = Member.builder()
                .loginType(registrationId)
                .nickname(providerUser.getUsername())
                .profileImage(providerUser.getPicture())
                .socialId(providerUser.getId())
                .email(providerUser.getEmail())
                .status(1)
                .build();

        //중복 회원 가입 방지
        if(memberRepository.existsByEmail(member.getEmail())){
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

    @Override
    @Transactional
    public Member updateMember(HttpServletRequest httpServletRequest, MemberRequestDTO.UpdateProfileDTO updateProfileDTO) {
        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        if(updateProfileDTO.getName() != null) member.setNickname(updateProfileDTO.getName());
        if(updateProfileDTO.getImage_url() != null) member.setProfileImage(updateProfileDTO.getImage_url());
        return member;
    }

    @Override
    @Transactional
    public Member deleteMember(HttpServletRequest httpServletRequest) {
        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        member.setStatus(0);
        return member;
    }

    /**
     * front 에서 정보 전달
     * @param joinMemberDTO
     * @return
     */
    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinMemberDTO joinMemberDTO) {
        Member member = Member.builder()
                .nickname(joinMemberDTO.getName())
                .email(joinMemberDTO.getEmail())
                .profileImage(joinMemberDTO.getProfile_image())
                .loginType(joinMemberDTO.getLogin_type())
                .status(1)
                .build();
        if(memberRepository.existsByEmail(member.getEmail())){
            return member;
        }
        else{
            return memberRepository.save(member);
        }
    }
}
