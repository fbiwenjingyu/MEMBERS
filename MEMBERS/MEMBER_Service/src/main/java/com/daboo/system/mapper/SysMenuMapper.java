package com.daboo.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;

import com.daboo.system.entity.SysMenu;

public interface SysMenuMapper extends GenericMapper<SysMenu, Long> {
	
	public int deleteByCondition(Map<String, Object> condition);
	
	public List<SysMenu> getAllMenus(Long userId);

}
