package com.planme.main.service.categoryService;

import com.planme.main.apiPayload.code.status.ErrorStatus;
import com.planme.main.apiPayload.exception.handler.CategoryHandler;
import com.planme.main.apiPayload.exception.handler.MemberHandler;
import com.planme.main.domain.Category;
import com.planme.main.domain.Member;
import com.planme.main.repository.CategoryRepository;
import com.planme.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService{

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public Category getCategory(Long id) {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId()).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));

        return category;
    }

    @Override
    public List<Category> getCategoryList() {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<Category> categoryList = categoryRepository.findAllByMember(member);
        return categoryList;
    }
}
