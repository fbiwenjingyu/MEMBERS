package com.daboo.system.mapper;

import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;

import com.daboo.system.entity.SysDetail;

public interface SysDetailMapper extends GenericMapper<SysDetail, Long> {

	public void deleteByCondition(Map<String, Object> map);

}
