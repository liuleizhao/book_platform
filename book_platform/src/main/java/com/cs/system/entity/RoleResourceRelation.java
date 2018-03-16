package com.cs.system.entity;

import java.util.Date;

import com.cs.common.entityenum.AuthorizeTypeEnum;
import com.cs.mvc.dao.BaseEntity;

/**
 * 角色资源关联实体
 */
public class RoleResourceRelation extends BaseEntity{
	
    private AuthorizeTypeEnum authorizeType = AuthorizeTypeEnum.NORMAL;
 
    private String resourceId;
 
    private String roleId;
 
    private Date createdDate = new Date();
 
    public AuthorizeTypeEnum getAuthorizeType() {
		return authorizeType;
	}

	public void setAuthorizeType(AuthorizeTypeEnum authorizeType) {
		this.authorizeType = authorizeType;
	}

	public String getResourceId() {
        return resourceId;
    }
 
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
 
    public String getRoleId() {
        return roleId;
    }
 
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
 
    public Date getCreatedDate() {
        return createdDate;
    }
 
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}