package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.MeStoryConverter;
import com.planme.main.domain.MeStoryFocus;
import com.planme.main.repository.MeStoryFocusRepository;
import com.planme.main.service.meStoryService.MeStoryCommandService;
import com.planme.main.service.meStoryService.MeStoryQueryService;
import com.planme.main.web.dto.MeStoryDTO.MeStoryRequestDTO;
import com.planme.main.web.dto.MeStoryDTO.MeStoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meStory")
@RequiredArgsConstructor
public class MeStoryRestController {

    private final MeStoryCommandService meStoryCommandService;
    private final MeStoryQueryService meStoryQueryService;
    MeStoryFocusRepository meStoryFocusRepository;

    @GetMapping("/{memberId}/{date}")
    public ApiResponse<MeStoryResponseDTO.ReadMeStoryResultListDTO> readMestoryByMemberIdAndDate(
            @PathVariable(name = "memberId") Long member_id,
            @PathVariable(name = "date") String date) {
        List<MeStoryResponseDTO.ReadMeStoryResultDTO> readMeStoryResult =
                meStoryQueryService.readMeStoryByMemberIdAndDate(member_id, date);

        MeStoryResponseDTO.ReadMeStoryResultListDTO readMeStoryResultList = MeStoryConverter.toGetFocusListDTO(readMeStoryResult);

        return ApiResponse.of(SuccessStatus.MESTORY_FOUND, readMeStoryResultList);
    }

    @PostMapping("/{categoryId}")
    public ApiResponse<MeStoryResponseDTO.PostMeStoryResultDTO> postMeStoryByCategoryId(
            @PathVariable (name = "categoryId") Long category_id,
            @RequestBody MeStoryRequestDTO.PostMeStoryFocusRequestDTO requestForm
            ){
        Pair<MeStoryFocus,Integer> meStoryFocus = meStoryCommandService.postMeStoryFocusByCategoryId(
                requestForm, category_id);

        if(meStoryFocus.getSecond() == 1){
            //update
            return ApiResponse.of(SuccessStatus.MESTORY_FOCUSTIME_UPDATED,
                    MeStoryConverter.toPostMeStoryFocusResultDTO(meStoryFocus.getFirst()));
        }
        else{
            //create
            return ApiResponse.of(SuccessStatus.MESTORY_FOCUSTIME_CREATED,
                    MeStoryConverter.toPostMeStoryFocusResultDTO(meStoryFocus.getFirst()));
        }
    }



}
