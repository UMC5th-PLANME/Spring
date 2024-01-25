package com.planme.main.oauth2;

import com.planme.main.converter.MemberConverter;
import com.planme.main.repository.MemberRepository;
import com.planme.main.web.dto.MemberDTO.MemberDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private final TokenService tokenService;
    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = tokenService.getJwtFromHeader((HttpServletRequest) request);

        if (token != null && tokenService.verifyToken(token)) {
            String email = tokenService.getUid(token);

            MemberDTO memberDTO = memberConverter.toMemberDTO(memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("사용자가 존재하지 않습니다.")));

            Authentication auth = getAuthentication(memberDTO);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(MemberDTO member) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
