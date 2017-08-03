package com.daboo.message.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.message.mapper.MOpinionMapper;
import com.daboo.message.mapper.MReplyMapper;
import com.daboo.message.model.MOpinionModel;
import com.daboo.message.model.MReplyModel;
import com.daboo.service.impl.base.BaseServiceImpl;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.model.MUserInfoModel;
@Service
@Transactional
public class MOpinionServiceImpl extends BaseServiceImpl<MOpinionModel,Integer> implements MOpinionService {

	private Logger logger = LoggerFactory.getLogger(MOpinionServiceImpl.class);
	@Autowired
	private MOpinionMapper mOpinionMapper;
	@Autowired
	private MReplyMapper mReplyMapper;
	@Autowired
	MUserInfoMapper mUserInfoMapper;
	@Autowired
	public void setMapper(MOpinionMapper mapper) {
		setGenericMapper(mapper);
	}

	/**
	 * @Description 根据id获取反馈的详情
	 * @param   id   反馈表的主键
	 * @return  反馈实体对象
	 * @author fengchao
	 */
	public MOpinionModel getOpinionDetail(Integer id) {
		
	    MOpinionModel model = null;
	    List<MReplyModel> replys = null;
	    try {
	    	model = mOpinionMapper.getObjectById(id);
	    	if(model != null){
	    		replys = mReplyMapper.getListByOpinionId(model.getId());
	    		if(replys != null && replys.size() > 0){
	    			model.setReplys(replys);
	    		}
	    		//获取回复人的姓名
	    		addReplyName(model);
	    		
	    	}
		} catch (Exception e) {
			logger.error("根据id获取所有回复异常",e);
		}
		return model;
	}
	/**
	 * @Description 根据id获取用户信息
	 * @param   id   回复表的主键
	 * @return  回复实体对象
	 * @author fengchao
	 */
	public void addReplyName(MOpinionModel model){
		
		if(model != null && model.getReplys() != null && model.getReplys().size() > 0){
			
			for(MReplyModel reply : model.getReplys()){
				String name = "";
				if(reply.getIsSystem().intValue() == 1){
					name = "系统管理员";
				}else{
					//根据主键id查询用户信息
					MUserInfoModel userModel =  mUserInfoMapper.findUserById(reply.getReplyId());
					if(userModel != null){
						name = userModel.getRealName();
					}
				}
				reply.setReplyName(name);
			}
			
		}
	}

	/**
	 * @Description 根据id列表删除反馈
	 * @param   ids  多个主键
	 * @author fengchao
	 */
	public void deleteOpinion(String ids) {
        //删除反馈
		mOpinionMapper.deleteOpinionByIds(ids);
		//删除反馈下的回复
		deleteReplyByIds(ids);
	}
	/**
	 * @Description 根据id列表删除反馈
	 * @param   ids  多个主键
	 * @author fengchao
	 */
	public void deleteReplyByIds(String ids){
		
		mReplyMapper.deleteByResponseId(ids);
	}
}
