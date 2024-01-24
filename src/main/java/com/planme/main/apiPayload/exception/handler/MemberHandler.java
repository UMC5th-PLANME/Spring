package com.planme.main.apiPayload.exception.handler;

import com.planme.main.apiPayload.code.BaseErrorCode;
import com.planme.main.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
