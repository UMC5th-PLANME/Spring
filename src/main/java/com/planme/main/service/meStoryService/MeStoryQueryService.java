package com.planme.main.service.meStoryService;

import com.planme.main.web.dto.MeStoryDTO.MeStoryResponseDTO;

import java.util.List;

public interface MeStoryQueryService {
    List<MeStoryResponseDTO.ReadMeStoryResultDTO> readMeStoryByMemberIdAndDate(Long member_id, String date);
}
