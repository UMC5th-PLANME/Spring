package com.planme.main.service.categoryService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.CategoryHandler;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.converter.CategoryConverter;
import com.planme.main.domain.Category;
import com.planme.main.domain.Member;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.MemberRepository;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    /**
     * TODO: 토큰에서 유저 정보 받아와서 해당 유저랑 매핑되도록 수정해야함
     *
     * @param request
     * @return
     */
    @Override
    @Transactional
    public Category createCategory(CategoryRequestDTO.CreateCategoryDto request) {

        Category category = CategoryConverter.toCategory(request);
        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        category.setMember(member);

        return categoryRepository.save(category);
    }

    /**
     * TODO: 토큰에서 유저 정보 받아와서 해당 유저랑 매핑되도록 수정해야함
     * 부모 엔티티를 제거하면 자식 엔티티는 고아 객체가 되어 알아서 제거된다고 하는데 일정을 먼저 지우는 로직이 필요할까?
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public CategoryResponseDTO.DeleteCategoryResultDTO deleteCategory(Long id) {

        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId()).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));
        CategoryResponseDTO.DeleteCategoryResultDTO deleteCategoryResultDTO = CategoryConverter.toDeleteResultDTO(category);

        categoryRepository.delete(category);

        return deleteCategoryResultDTO;
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, CategoryRequestDTO.UpdateCategoryDto request) {

        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId()).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));

        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getEmoticon() != null) {
            category.setEmoticon(request.getEmoticon());
        }
        if (request.getColor() != null) {
            category.setColor(request.getColor());
        }

        return category;
    }

    @Override
    @Transactional
    public Category changeCategoryStatus(Long id) {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId()).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));

        category.setMeStoryHidden(!category.isMeStoryHidden());

        return category;
    }


}
