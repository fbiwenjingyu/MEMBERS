package com.daboo.system.service;

import java.util.List;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.service.base.BaseService;
import com.daboo.system.entity.SysRole;
import com.daboo.system.entity.SysRoleMenu;
import com.daboo.system.entity.SysRoleUser;

@RemoteService
public interface SysRoleService extends BaseService<SysRole,Long>{
	
	public List<SysRoleMenu> selectRoleMenuByCondition(Long roleId);	
	
	public List<SysRoleUser> selectSUserRoleByCondition(Long roleId);	
	
	public void  bindRoleMenu(Long roleId,String[] menuIds);
	
	public void  bindRoleUser(Long roleId,String[] userIds);
	
	public  void  deleteRoleByIds(String[] roleIds);
	
	public List<SysRole> selectRolesByUserId(Long userId);
	
}
