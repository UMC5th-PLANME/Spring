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

    // 스케줄 관련 응답
    SCHEDULE_FOUND(HttpStatus.OK, "SCHEDULE2005", "스케줄 조회 성공입니다."),
    SCHEDULE_DELETE(HttpStatus.OK, "SCHEDULE2006", "스케줄 삭제 성공입니다.");

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
