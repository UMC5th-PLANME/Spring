package com.planme.main.service;

import com.planme.main.domain.Focus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FocusQueryService {
    Focus getFocusByCategoryId(Long categoryId);
}
