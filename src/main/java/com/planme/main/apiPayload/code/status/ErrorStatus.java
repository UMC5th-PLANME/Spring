package com.planme.main.apiPayload.code.status;

import com.planme.main.apiPayload.code.BaseErrorCode;
import com.planme.main.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // Ror test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    // FoodCategory Error
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4001", "음식 카테고리가 없습니다."),

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "가게가 없습니다."),

    MISSION_ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION4001", "이미 도전 중인 미션입니다."),

    //페이지 관련
    PAGE_NOT_VALID(HttpStatus.BAD_REQUEST, "PAGE4001","유효하지 않은 페이지 요청입니다."),

    //Focus 관련
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY4001", "해당 카테고리 ID가 없습니다."),

    //meStory 관련
    MESTORY_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MESTORY4001", "존재하지 않는 MEMBER_ID 입니다"),
    MESTORY_DATE_FORM_ERROR(HttpStatus.NOT_FOUND, "MESTORY4002", "잘못된 날짜 형식입니다"),
    MESTORY_FOCUS_SETTING_NOT_FOUND(HttpStatus.NOT_FOUND, "MESTORY4003", "FOCUS 설정이 존재하지 않습니다"),



    ;



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
