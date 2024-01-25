package com.planme.main.service.categoryService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.CategoryHandler;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.converter.CategoryConverter;
import com.planme.main.domain.Category;
import com.planme.main.domain.Member;
import com.planme.main.oauth2.TokenService;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.MemberRepository;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    /**
     * @param request
     * @return
     */
    @Override
    @Transactional
    public Category createCategory(HttpServletRequest httpServletRequest, CategoryRequestDTO.CreateCategoryDto request) {

        Category category = CategoryConverter.toCategory(request);
        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        category.setMember(member);

        return categoryRepository.save(category);
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public CategoryResponseDTO.DeleteCategoryResultDTO deleteCategory(HttpServletRequest httpServletRequest, Long id) {

        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId()).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));
        CategoryResponseDTO.DeleteCategoryResultDTO deleteCategoryResultDTO = CategoryConverter.toDeleteResultDTO(category);

        categoryRepository.delete(category);

        return deleteCategoryResultDTO;
    }

    @Override
    @Transactional
    public Category updateCategory(HttpServletRequest httpServletRequest, Long id, CategoryRequestDTO.UpdateCategoryDto request) {

        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
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
    public Category changeCategoryStatus(HttpServletRequest httpServletRequest, Long id) {
        String email = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId()).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));

        category.setMeStoryHidden(!category.isMeStoryHidden());

        return category;
    }


}
