package com.daboo.system.entity;

import java.io.Serializable;

import org.apache.ibatis.annotation.myibatis.Table;

@Table(pkId = "roleUserId", tabName = "T_S_ROLE_USER")
public class SysRoleUser implements Serializable{

	private static final long serialVersionUID = -4959799737486238494L;

	//自增长，主键
	private Long roleUserId; 

	//用户ID
	private Long userId; 

	//角色ID
	private Long roleId; 

	public Long  getRoleUserId(){
		return this.roleUserId;
	}

	public void setRoleUserId(Long roleUserId){
		this.roleUserId=roleUserId;
	}

	public Long  getUserId(){
		return this.userId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long  getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}
}
