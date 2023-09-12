package com.deyaco.framework.core.api;

import com.deyaco.framework.core.util.JsonUtils;

import java.io.Serializable;

public class ApiObject implements Serializable {
    private static final long serialVersionUID = 1L;
    public ApiObject() {
    }
    public String toString() {
        return JsonUtils.toJsonString(this);
    }
 }
