package com.daboo.system.service;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.service.base.BaseService;
import com.daboo.system.entity.SysUser;

@RemoteService
public interface SysUserService extends BaseService<SysUser,Long>{
	
	public void updateUserPwd(SysUser record);
	
	public  void deleteUserByIds(String[] userIds);

}
