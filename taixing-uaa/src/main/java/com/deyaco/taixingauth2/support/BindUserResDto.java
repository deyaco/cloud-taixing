package com.deyaco.taixingauth2.support;

import com.deyaco.commons.api.ApiObject;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class BindUserResDto extends ApiObject {
    private static final long serialVersionUID = 1L;

    /**
     * id int8
     */
    private Long id;

    /**
     * 创建人id int8
     */
    private Long createUserId;

    /**
     * 创建人 varchar(64)
     */
    private String createUserName;

    /**
     * 创建时间 timestamptz(6)
     */
    private OffsetDateTime createTime;

    /**
     * 修改人id int8
     */
    private Long modifyUserId;

    /**
     * 修改人 varchar(64)
     */
    private String modifyUserName;

    /**
     * 修改时间 timestamptz(6)
     */
    private OffsetDateTime modifyTime;

    /**
     * 版本号 int8
     */
    private Long revision;

    /**
     * 邮箱 varchar(64)
     */
    private String mail;

    /**
     * 手机号 varchar(64)
     */
    private String mobile;

    /**
     * 管理员标识 bool
     */
    private Boolean administrator;

    /**
     * 启用标识 bool
     */
    private Boolean enabled;

    /**
     * 密码（可选） varchar(256)
     */
    private String secret;

    /**
     * 姓名 varchar(64)
     */
    private String displayName;

    /**
     * 性别（1：男；0：女；2：其他）
     */
    private String gender;

    /**
     * 工号 varchar(64)
     */
    private String employeeNumber;

    /**
     * 头像fid varchar(64)
     */
    private String avatarFid;
}
