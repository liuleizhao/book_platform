package com.cs.system.entity;

import java.util.Date;

import com.cs.common.entityenum.AuthorizeTypeEnum;
import com.cs.mvc.dao.BaseEntity;


public class UserRoleRelation  extends BaseEntity{
 
    private AuthorizeTypeEnum authorizeType = AuthorizeTypeEnum.NORMAL;
 
    private String roleId;
 
    private String userId;
 
    private Date createdDate = new Date();
    
    private String userName;
 
    public AuthorizeTypeEnum getAuthorizeType() {
		return authorizeType;
	}

	public void setAuthorizeType(AuthorizeTypeEnum authorizeType) {
		this.authorizeType = authorizeType;
	}

	public String getRoleId() {
        return roleId;
    }
 
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
 
    public String getUserId() {
        return userId;
    }
 
    public void setUserId(String userId) {
        this.userId = userId;
    }
 
    public Date getCreatedDate() {
        return createdDate;
    }
 
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
   
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}