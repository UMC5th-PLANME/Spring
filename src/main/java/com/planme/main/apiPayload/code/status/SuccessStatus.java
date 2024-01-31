package com.planme.main.apiPayload.code.status;

import com.planme.main.apiPayload.code.BaseCode;
import com.planme.main.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    // 멤버 관련 응답
    MEMBER_FOUND(HttpStatus.OK,"MEMBER2001", "회원을 조회했습니다."),
    MEMBER_TERMS_AGREED(HttpStatus.OK, "MEMBER2002", "회원의 이용약관 동의했습니다."),
    MEMBER_UPDATE(HttpStatus.OK, "MEMBER2003", "회원정보를 업데이트 했습니다. "),
    MEMBER_DELETE(HttpStatus.OK, "MEMBER2004", "회원 탈퇴 성공"),
    MEMBER_JOIN(HttpStatus.OK, "MEMBER2005", "로그인 성공"),

    //이미지 관련 응답
    IMAGE_FOUND(HttpStatus.OK, "IMAGE2001","서버에 이미지 저장 성공"),

    // 스케줄 관련 응답
    SCHEDULE_FOUND(HttpStatus.OK, "SCHEDULE2002", "스케줄 조회 성공입니다."),
    SCHEDULE_UPDATE(HttpStatus.OK, "SCHEDULE2003", "스케줄 수정 성공입니다."),
    SCHEDULE_DELETE(HttpStatus.OK, "SCHEDULE2004", "스케줄 삭제 성공입니다."),

    // 카테고리 관련 응답
    CATEGORY_CREATED(HttpStatus.OK,"CATEGORY2001","카테고리가 추가되었습니다."),
    CATEGORY_DELETED(HttpStatus.OK,"CATEGORY2002","카테고리가 삭제되었습니다."),
    CATEGORY_UPDATED(HttpStatus.OK,"CATEGORY2003","카테고리가 수정되었습니다."),
    CATEGORY_STATUS_CHANGED(HttpStatus.OK,"CATEGORY2004","카테고리 숨김 설정이 변경되었습니다."),
    CATEGORY_FOUND(HttpStatus.OK,"CATEGORY2005","카테고리를 조회했습니다."),

    // focus 관련 응답
    FOCUS_FOUND(HttpStatus.OK, "FOCUS2001", "FOCUS 정보 조회 성공입니다"),
    FOCUS_CREATED(HttpStatus.OK, "FOCUS2011", "FOCUS 정보 생성 성공입니다"),
    FOCUS_UPDATED(HttpStatus.OK, "FOCUS2012", "FOCUS 정보 업데이트 성공입니다"),

    // meStory 관련 응답
    MESTORY_FOUND(HttpStatus.OK, "MESTORY2001", "MESTORY 정보 조회 성공입니다"),
    MESTORY_FOCUSTIME_CREATED(HttpStatus.OK, "MESTORY2011", "MESTORY 집중 시간 생성 성공입니다"),
    MESTORY_FOCUSTIME_UPDATED(HttpStatus.OK, "MESTORY2012", "MESTORY 집중 시간 갱신 성공입니다");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
