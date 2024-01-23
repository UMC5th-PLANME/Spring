package com.planme.main.service.categoryService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.CategoryHandler;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.domain.Category;
import com.planme.main.domain.Member;
import com.planme.main.oauth2.TokenService;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService{

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    @Override
    public Category getCategory(HttpServletRequest httpServletRequest, Long id) {
        String email = tokenService.getUid(httpServletRequest.getHeader("Auth"));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId()).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));

        return category;
    }

    @Override
    public List<Category> getCategoryList(HttpServletRequest httpServletRequest) {
        String email = tokenService.getUid(httpServletRequest.getHeader("Auth"));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<Category> categoryList = categoryRepository.findAllByMember(member);
        return categoryList;
    }
}
