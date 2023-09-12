package com.deyaco.framework.ddd.api;

public interface IApiStatus {

    int getCode();

    String name();

    default boolean isOk() {
        int code = this.getCode();
        return code >= 200 && code <= 208;
    }
}
