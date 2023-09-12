package com.deyaco.framework.amqp;

import com.deyaco.framework.core.api.ApiObject;


public class MqObject extends ApiObject {
    private Integer retryCnt;
    private String lastTryInfo;
    private String busiObjectStr;

    public MqObject() {
    }

    public void setRetryCnt(final Integer retryCnt) {
        this.retryCnt = retryCnt;
    }

    public void setLastTryInfo(final String lastTryInfo) {
        this.lastTryInfo = lastTryInfo;
    }

    public void setBusiObjectStr(final String busiObjectStr) {
        this.busiObjectStr = busiObjectStr;
    }

    public Integer getRetryCnt() {
        return this.retryCnt;
    }

    public String getLastTryInfo() {
        return this.lastTryInfo;
    }

    public String getBusiObjectStr() {
        return this.busiObjectStr;
    }
}