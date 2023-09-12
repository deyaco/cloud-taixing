package com.deyaco.framework.core;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.deyaco.framework.core.api.ApiObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Whoami extends ApiObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final TransmittableThreadLocal<Whoami> THREAD_LOCAL = new TransmittableThreadLocal();
    private Long tenantId;
    private Long appId;
    private String appVersion;
    private Long operatorId;
    private String operatorName;
    private String authorization;
    private String traceId;
    private Boolean administrator = false;
    private String employeeNumber;
    private Map<String, Object> metadata = new LinkedHashMap();
    private List<String> bizAdministrators = new ArrayList();

    public Whoami() {
    }

    @JsonIgnore
    public boolean isPresent() {
        return this.operatorId != null && this.operatorName != null;
    }

    public static Whoami get() {
        Whoami value = (Whoami)THREAD_LOCAL.get();
        if (value == null) {
            value = new Whoami();
        }

        return value;
    }

    public static void set(Whoami whoami) {
        THREAD_LOCAL.set(whoami);
    }

    public static void cleanUp() {
        THREAD_LOCAL.remove();
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public Long getAppId() {
        return this.appId;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public Long getOperatorId() {
        return this.operatorId;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public String getAuthorization() {
        return this.authorization;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public Boolean getAdministrator() {
        return this.administrator;
    }

    public String getEmployeeNumber() {
        return this.employeeNumber;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public List<String> getBizAdministrators() {
        return this.bizAdministrators;
    }

    public void setTenantId(final Long tenantId) {
        this.tenantId = tenantId;
    }

    public void setAppId(final Long appId) {
        this.appId = appId;
    }

    public void setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
    }

    public void setOperatorId(final Long operatorId) {
        this.operatorId = operatorId;
    }

    public void setOperatorName(final String operatorName) {
        this.operatorName = operatorName;
    }

    public void setAuthorization(final String authorization) {
        this.authorization = authorization;
    }

    public void setTraceId(final String traceId) {
        this.traceId = traceId;
    }

    public void setAdministrator(final Boolean administrator) {
        this.administrator = administrator;
    }

    public void setEmployeeNumber(final String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setMetadata(final Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public void setBizAdministrators(final List<String> bizAdministrators) {
        this.bizAdministrators = bizAdministrators;
    }
}