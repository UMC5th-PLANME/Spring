package com.planme.main.service.categoryService;

import com.planme.main.converter.CategoryConverter;
import com.planme.main.domain.Category;
import com.planme.main.domain.Member;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.MemberRepository;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService{

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Category createCategory(CategoryRequestDTO.CreateCategoryDto request) {
        Category category = CategoryConverter.toCategory(request);

        // TODO: 토큰에서 유저 정보 받아와서 해당 유저랑 매핑되도록 수정해야함
        Member member = memberRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("사용자가 존재하지 않습니다."));

        category.setMember(member);

        return categoryRepository.save(category);
    }
}
