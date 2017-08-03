package com.daboo.system.service;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.service.base.BaseService;
import com.daboo.system.entity.SysLog;

@RemoteService
public interface SysLogService extends BaseService<SysLog,Long>{

}
