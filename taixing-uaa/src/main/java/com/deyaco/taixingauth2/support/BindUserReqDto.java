package com.deyaco.taixingauth2.support;

import com.deyaco.commons.api.ApiObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindUserReqDto extends ApiObject {

    private static final long serialVersionUID = 1L;


    /**
     * 账户类型名称(user.account.type) varchar(16)
     */
    private String accountTypeEnum;

    /**
     * 账户名 varchar(64)
     */
    private String accountName;

    /**
     * 邮箱 varchar(64)
     */
    private String mail;

    /**
     * 手机号 varchar(64)
     */
    private String mobile;

    /**
     * 工号 varchar(32)
     */
    private String employeeNumber;

    /**
     * 姓名 varchar(64)
     */
    private String displayName;

    /**
     * ip
     */
    private String ip;

    /**
     * 区域 varchar(2147483647)
     */
    private String locale;

    /**
     * 接受语言 varchar(2147483647)
     */
    private String acceptLanguage;

    /**
     * loginRequest
     */
    private Boolean loginRequest;

    /**
     * userAgent
     */
    private String userAgent;

    /**
     * tenantId
     */
    private Long tenantId;

    /**
     * appId
     */
    private Long appId;

    /**
     * appVersion
     */
    private String appVersion;

}