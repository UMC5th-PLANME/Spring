package com.planme.main.oauth2.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Map;

public interface ProviderUser {
    String getId();
    String getUsername();
    String getProvider();
    String getEmail();
    String getPicture();
    List<SimpleGrantedAuthority> getAuthorities();
    Map<String, Object> getAttributes();
}
