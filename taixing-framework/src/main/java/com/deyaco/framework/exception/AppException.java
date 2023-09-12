package com.deyaco.framework.exception;

import com.deyaco.framework.ddd.api.IApiStatus;

public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final IApiStatus apiStatus;
    private Object data;

    public AppException(IApiStatus apiStatus) {
        this.apiStatus = apiStatus;
    }

    public AppException(IApiStatus apiStatus, String message) {
        super(message);
        this.apiStatus = apiStatus;
    }

    public AppException(IApiStatus apiStatus, String message, Object data) {
        super(message);
        this.apiStatus = apiStatus;
        this.data = data;
    }

    public AppException(IApiStatus apiStatus, String message, Throwable cause) {
        super(message, cause);
        this.apiStatus = apiStatus;
    }

    public IApiStatus getApiStatus() {
        return this.apiStatus;
    }

    public Object getData() {
        return this.data;
    }
}