package com.daboo.system.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.service.impl.base.BaseServiceImpl;
import com.daboo.system.entity.SysDict;
import com.daboo.system.mapper.SysDetailMapper;
import com.daboo.system.mapper.SysDictMapper;
import com.daboo.web.context.SystemCache;

@Service
@Transactional
public class SysDictServiceImpl extends BaseServiceImpl<SysDict, Long> implements SysDictService {
	@Autowired
	private SysDictMapper mapper;
	@Autowired
	private SysDetailMapper sysDetailMapper;

	@Autowired
	public void setMapper(SysDictMapper mapper) {
		setGenericMapper(mapper);
	}

	@Override
	public void persist(SysDict record) {
		record.setDictStatus(1);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		super.persist(record);
		SystemCache.reCacheDict();
	}

	

	@Override
	public int removeById(Long dictId) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("dictId", dictId);
		sysDetailMapper.deleteByCondition(condititon);
		int flag = mapper.deleteById(dictId);
		SystemCache.reCacheDict();
		return flag;
	}

	@Override
	public Map<String, String> getDetailNameMap(String dictName) {
		return DictCache.getDetailNameMapByDictName(dictName);
	}

	@Override
	public Map<String, String> getDetailValueMap(String dictName) {
		return DictCache.getDetailValueMapByDictName(dictName);
	}

	@Override
	public String getDetailValue(String dictName, String detailName) {
		return DictCache.getDetailValue(dictName, detailName);
	}
}
