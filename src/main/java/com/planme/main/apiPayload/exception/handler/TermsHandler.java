package com.planme.main.apiPayload.exception.handler;

import com.planme.main.apiPayload.code.BaseErrorCode;
import com.planme.main.apiPayload.exception.GeneralException;

public class TermsHandler extends GeneralException {
    public TermsHandler(BaseErrorCode code) {
        super(code);
    }
}
