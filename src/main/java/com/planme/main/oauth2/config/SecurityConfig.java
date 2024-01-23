package com.planme.main.oauth2.config;

import com.planme.main.oauth2.authority.CustomAuthorityMapper;
import com.planme.main.repository.memberRepository.MemberRepository;
import com.planme.main.service.memberService.CustomOAuth2UserService;
import com.planme.main.service.memberService.CustomOidcUserService;
import com.planme.main.converter.MemberConverter;
import com.planme.main.oauth2.JwtAuthFilter;
import com.planme.main.oauth2.OAuth2SuccessHandler;
import com.planme.main.oauth2.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;
    private final OAuth2SuccessHandler successHandler;
    private final TokenService tokenService;
    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers( new AntPathRequestMatcher("/login")).permitAll()
                        .anyRequest().authenticated())

                .addFilterBefore(new JwtAuthFilter(tokenService, memberRepository,memberConverter), UsernamePasswordAuthenticationFilter.class)

                .oauth2Login(oauth2 -> oauth2
                        .successHandler(successHandler)
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)
                                .oidcUserService(customOidcUserService)))

                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
        ;

        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        return new CustomAuthorityMapper();
    }
}
