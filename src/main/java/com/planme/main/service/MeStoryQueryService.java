package com.planme.main.service;

import com.planme.main.web.dto.MeStoryResponseDTO;

import java.util.List;

public interface MeStoryQueryService {
    List<MeStoryResponseDTO.ReadMeStoryResultDTO> readMeStoryByMemberIdAndDate(Long member_id, String date);
}
