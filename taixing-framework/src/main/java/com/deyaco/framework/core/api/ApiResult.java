package com.deyaco.framework.core.api;

import com.deyaco.framework.core.contants.SysConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("请求响应对象")
@JsonPropertyOrder({"code", "msg", "data"})
public class ApiResult<T> extends ApiObject {
    private static final long serialVersionUID = 1L;
    @JsonProperty(
        index = 10
    )
    @ApiModelProperty(
        value = "业务返回码，200表示成功（接受2XX系列）",
        example = "200",
        position = 10,
        required = true
    )
    private int code;
    @JsonProperty(
        index = 20
    )
    @ApiModelProperty(
        value = "业务消息提示",
        example = "ok",
        position = 20,
        required = true
    )
    private String msg;
    @JsonProperty(
        index = 30
    )
    @ApiModelProperty(
        value = "业务数据对象",
        position = 30,
        required = false
    )
    private T data;
    @JsonProperty(
        index = 40
    )
    @ApiModelProperty(
        value = "异常返回内容",
        position = 40,
        required = false
    )
    private String errorMsg;

    @JsonIgnore
    public boolean isOk() {
        return this.code >= 200 && this.code <= 208;
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult(200, "ok", data, "");
    }

    public static <T> ApiResult<T> created(T data) {
        return new ApiResult(201, "Created", data, "");
    }

    public static <T> ApiResult<T> accepted(T data) {
        return new ApiResult(202, "Accepted", data, "");
    }

    public static <T> ApiResult<T> fail(int code, String msg) {
        return new ApiResult(code, msg, (Object)null, "");
    }

    public static <T> ApiResult<T> fail(int code, String msg, T data) {
        return new ApiResult(code, msg, data, "");
    }

    public static <T> ApiResult<T> fail(int code, String msg, T data, String errorMsg) {
        return new ApiResult(code, msg, data, errorMsg);
    }

    public T getData() {
        return !this.isOk() && SysConstants.SKIP_API_DATA ? null : this.data;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    @JsonProperty(
        index = 10
    )
    public void setCode(final int code) {
        this.code = code;
    }

    @JsonProperty(
        index = 20
    )
    public void setMsg(final String msg) {
        this.msg = msg;
    }

    @JsonProperty(
        index = 30
    )
    public void setData(final T data) {
        this.data = data;
    }

    @JsonProperty(
        index = 40
    )
    public void setErrorMsg(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ApiResult() {
    }

    public ApiResult(final int code, final String msg, final T data, final String errorMsg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.errorMsg = errorMsg;
    }
}