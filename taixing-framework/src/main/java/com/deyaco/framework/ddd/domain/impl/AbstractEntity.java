package com.deyaco.framework.ddd.domain.impl;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.deyaco.framework.core.util.JsonUtils;
import com.deyaco.framework.ddd.domain.IEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.OffsetDateTime;

public abstract class AbstractEntity<T extends Model<T>> extends Model<T> implements IEntity {
    private static final long serialVersionUID = 1L;
    public static final String ID = "id";
    public static final String TENANT_ID = "tenant_id";
    public static final String APP_ID = "app_id";
    public static final String CREATE_USER_ID = "create_user_id";
    public static final String CREATE_USER_NAME = "create_user_name";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_USER_ID = "modify_user_id";
    public static final String MODIFY_USER_NAME = "modify_user_name";
    public static final String MODIFY_TIME = "modify_time";
    public static final String REVISION = "revision";
    public static final String DELETED = "deleted";
    @TableId(
            value = "id",
            type = IdType.INPUT
    )
    protected Long id;
    @TableField(
            fill = FieldFill.INSERT
    )
    protected Long tenantId;
    @TableField(
            fill = FieldFill.INSERT
    )
    protected Long appId;
    @TableField(
            fill = FieldFill.INSERT
    )
    protected Long createUserId;
    @TableField(
            fill = FieldFill.INSERT
    )
    protected String createUserName;
    @TableField(
            insertStrategy = FieldStrategy.NOT_NULL
    )
    protected OffsetDateTime createTime;
    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    protected Long modifyUserId;
    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    protected String modifyUserName;
    @TableField(
            update = "now()",
            fill = FieldFill.UPDATE,
            insertStrategy = FieldStrategy.NOT_NULL
    )
    protected OffsetDateTime modifyTime;
    @Version
    @TableField(
            update = "%s+1",
            fill = FieldFill.UPDATE,
            insertStrategy = FieldStrategy.NEVER
    )
    protected Long revision;
    @TableLogic(
            value = "false",
            delval = "true"
    )
    @TableField(
            insertStrategy = FieldStrategy.NEVER
    )
    protected Boolean deleted;

    public AbstractEntity() {
    }

    public String toString() {
        return JsonUtils.toJsonString(this);
    }

    @JsonIgnore
    protected final Serializable pkVal() {
        return this.id;
    }

    public Long getId() {
        return this.id;
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public Long getAppId() {
        return this.appId;
    }

    public Long getCreateUserId() {
        return this.createUserId;
    }

    public String getCreateUserName() {
        return this.createUserName;
    }

    public OffsetDateTime getCreateTime() {
        return this.createTime;
    }

    public Long getModifyUserId() {
        return this.modifyUserId;
    }

    public String getModifyUserName() {
        return this.modifyUserName;
    }

    public OffsetDateTime getModifyTime() {
        return this.modifyTime;
    }

    public Long getRevision() {
        return this.revision;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setTenantId(final Long tenantId) {
        this.tenantId = tenantId;
    }

    public void setAppId(final Long appId) {
        this.appId = appId;
    }

    public void setCreateUserId(final Long createUserId) {
        this.createUserId = createUserId;
    }

    public void setCreateUserName(final String createUserName) {
        this.createUserName = createUserName;
    }

    public void setCreateTime(final OffsetDateTime createTime) {
        this.createTime = createTime;
    }

    public void setModifyUserId(final Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public void setModifyUserName(final String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    public void setModifyTime(final OffsetDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setRevision(final Long revision) {
        this.revision = revision;
    }

    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }
}