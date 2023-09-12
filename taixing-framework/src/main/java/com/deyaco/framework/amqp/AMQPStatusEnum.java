package com.deyaco.framework.amqp;

import com.deyaco.framework.ddd.api.IApiStatus;

public enum AMQPStatusEnum implements IApiStatus {
    FAIL_500(500),
    FAIL_501(501);

    private final int code;

    public int getCode() {
        return this.code;
    }

    private AMQPStatusEnum(final int code) {
        this.code = code;
    }
}