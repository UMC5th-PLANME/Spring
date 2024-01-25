package com.planme.main.oauth2.converter;

public interface ProviderUserConverter <T, R> {
    R converter(T t);
}
