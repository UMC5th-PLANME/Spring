package com.planme.main.oauth2.config;

import com.planme.main.oauth2.authority.CustomAuthorityMapper;
import com.planme.main.service.memberService.CustomOAuth2UserService;
import com.planme.main.service.memberService.CustomOidcUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;

    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(
                auth->auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/")
                        ).permitAll()
                        .anyRequest().authenticated()
        )
                .oauth2Login(
                        oauth2 -> oauth2
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig
                                                .userService(customOAuth2UserService)
                                                .oidcUserService(customOidcUserService)
                                        )
                );
        http.logout(logout -> logout
                .logoutSuccessUrl("/")
        );
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper(){
        return new CustomAuthorityMapper();
    }
}
