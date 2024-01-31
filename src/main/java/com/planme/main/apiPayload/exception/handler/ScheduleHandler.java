package com.planme.main.apiPayload.exception.handler;

import com.planme.main.apiPayload.code.BaseErrorCode;
import com.planme.main.apiPayload.exception.GeneralException;

public class ScheduleHandler extends GeneralException {
    public ScheduleHandler (BaseErrorCode errorCode){
        super(errorCode);
    }
}
