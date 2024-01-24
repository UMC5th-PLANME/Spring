package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.MemberConverter;
import com.planme.main.domain.Member;
import com.planme.main.service.memberService.MemberService;
import com.planme.main.web.dto.MemberDTO.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberRestController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;
    @GetMapping
    public ApiResponse<MemberDTO> getMember(HttpServletRequest httpServletRequest){
        Member member = memberService.getMember(httpServletRequest);
        return ApiResponse.of(SuccessStatus.MEMBER_FOUND,memberConverter.toMemberDTO(member));
    }
}
