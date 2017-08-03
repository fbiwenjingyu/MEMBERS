package com.daboo.system.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.util.MD5Encoder;
import com.daboo.service.impl.base.BaseServiceImpl;
import com.daboo.system.entity.SysUser;
import com.daboo.system.mapper.SysRoleUserMapper;
import com.daboo.system.mapper.SysUserMapper;

@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long>
		implements SysUserService {
	
	@Autowired
	private SysRoleUserMapper roleUsermapper;
	@Autowired
	private SysUserMapper  sysUserMapper;
	@Autowired
	public void setMapper(SysUserMapper mapper) {
		setGenericMapper(mapper);
	}
	
	@Override
	public void persist(SysUser record){
		record.setUserStatus(1);
		record.setUserType(1);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		super.persist(record);
	}
	
	@Override
	public int updateById(SysUser record){
		record.setUpdateTime(new Date());
		int flag = super.updateById(record);
		return flag;
	}
	
	@Override
	public void updateUserPwd(SysUser record) {
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		record.setUpdateTime(new Date());
		super.updateById(record);
	}
	
	public  void deleteUserByIds(String[] userIds){
		for(String userId : userIds){
			//删除用户
			removeById(Long.parseLong(userId));
			//删除用户和角色的绑定关系
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("userId", userId);					
			roleUsermapper.deleteByCondition(condition);
		}
		
	}

	
}
