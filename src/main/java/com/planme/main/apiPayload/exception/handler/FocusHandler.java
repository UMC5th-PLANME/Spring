package com.planme.main.apiPayload.exception.handler;

import com.planme.main.apiPayload.code.BaseErrorCode;
import com.planme.main.apiPayload.exception.GeneralException;

public class FocusHandler extends GeneralException {
    public FocusHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}