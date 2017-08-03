package com.daboo.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.daboo.system.entity.SysLog;
import com.daboo.system.mapper.SysLogMapper;

@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Long> implements
		SysLogService {

	@Autowired
	public void setMapper(SysLogMapper mapper) {
		setGenericMapper(mapper);
	}
}
