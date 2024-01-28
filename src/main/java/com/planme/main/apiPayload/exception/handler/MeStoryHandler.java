package com.planme.main.apiPayload.exception.handler;

import com.planme.main.apiPayload.code.BaseErrorCode;
import com.planme.main.apiPayload.exception.GeneralException;

public class MeStoryHandler extends GeneralException {
    public MeStoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}