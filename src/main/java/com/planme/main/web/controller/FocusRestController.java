package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.FocusConverter;
import com.planme.main.domain.Focus;
import com.planme.main.service.focusService.FocusCommandService;
import com.planme.main.service.focusService.FocusQueryService;
import com.planme.main.web.dto.FocusDTO.FocusRequestDTO;
import com.planme.main.web.dto.FocusDTO.FocusResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.util.Pair;

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

    @PostMapping("/{categoryId}")
    public ApiResponse<FocusResponseDTO.PostFocusResultDTO> postFocusByCategoryId(
            @PathVariable(name = "categoryId") Long category_id,
            @RequestBody FocusRequestDTO.PostFocusDTO requestForm){
        Pair<Focus,Integer> focus = focusCommandService.postFocusByCategoryId(requestForm, category_id);

        if(focus.getSecond() == 1){
            //update
            return ApiResponse.of(SuccessStatus.FOCUS_UPDATED, FocusConverter.toPostFocusResultDTO(focus.getFirst()));
        }
        else{
            //create
            return ApiResponse.of(SuccessStatus.FOCUS_CREATED, FocusConverter.toPostFocusResultDTO(focus.getFirst()));
        }

    }

}
