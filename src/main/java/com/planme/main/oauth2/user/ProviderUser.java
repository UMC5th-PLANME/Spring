package com.planme.main.oauth2.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

public interface ProviderUser {
    String getId();
    String getUsername();
    String getProvider();
    String getEmail();
    List<? extends GrantedAuthority> getAuthorities();
    Map<String, Object> getAttributes();
}
