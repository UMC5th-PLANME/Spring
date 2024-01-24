package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.FocusConverter;
import com.planme.main.domain.Focus;
import com.planme.main.service.FocusCommandService;
import com.planme.main.service.FocusQueryService;
import com.planme.main.web.dto.FocusResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/focus")
@RequiredArgsConstructor
public class FocusRestController {

    private final FocusCommandService focusCommandService;
    private final FocusQueryService focusQueryService;

    @GetMapping("/{categoryId}")
    public ApiResponse<FocusResponseDTO.ReadFocusResultDTO> readFocusByCategoryId(
            @PathVariable(name = "categoryId") Long category_id) {
        Focus focus = focusQueryService.getFocusByCategoryId(category_id);
        return ApiResponse.of(SuccessStatus.FOCUS_FOUND, FocusConverter.toReadFocusResultDTO(focus));
    }
}
