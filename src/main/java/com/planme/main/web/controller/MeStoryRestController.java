package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.MeStoryConverter;
import com.planme.main.service.MeStoryCommandService;
import com.planme.main.service.MeStoryQueryService;
import com.planme.main.web.dto.MeStoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/meStory")
@RequiredArgsConstructor
public class MeStoryRestController {

    private final MeStoryCommandService meStoryCommandService;
    private final MeStoryQueryService meStoryQueryService;

    @GetMapping("/{memberId}/{date}")
    public ApiResponse<MeStoryResponseDTO.ReadMeStoryResultListDTO> readMestoryByMemberIdAndDate(
            @PathVariable(name = "memberId") Long member_id,
            @PathVariable(name = "date") String date) {
        List<MeStoryResponseDTO.ReadMeStoryResultDTO> readMeStoryResult =
                meStoryQueryService.readMeStoryByMemberIdAndDate(member_id, date);

        MeStoryResponseDTO.ReadMeStoryResultListDTO readMeStoryResultList = MeStoryConverter.toGetFocusListDTO(readMeStoryResult);

        return ApiResponse.of(SuccessStatus.MESTORY_FOUND, readMeStoryResultList);
    }



}
