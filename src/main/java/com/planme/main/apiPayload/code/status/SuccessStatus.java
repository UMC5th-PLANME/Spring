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
