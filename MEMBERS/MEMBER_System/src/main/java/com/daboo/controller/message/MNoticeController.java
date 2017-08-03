package com.daboo.controller.message;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daboo.core.util.ConfigProperty;
import com.daboo.core.util.FileUtil;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.model.MConditions;
import com.daboo.message.model.MNoticeModel;
import com.daboo.message.service.MNoticeService;
import com.daboo.system.entity.SysUser;

/**
 * 
  * @ClassName: MNoticeController
  * @Description: 公告
  * @author 马正正
  * @date 2015年10月27日
 */
@Controller
@RequestMapping(value = "/notice")
public class MNoticeController extends BaseController {
	Logger log = LoggerFactory.getLogger(getClass());
	private String imgDir=ConfigProperty.getProperty("baidu.ueditor.imgDir");
	private String imgPathUrl=imgDir+"\\d{8}_\\d{19}(\\.[a-zA-Z]{3,4})?";
	private String imgPathLocal="/ueditor/jsp/upload/image/";
	private int imgNameBegin=imgDir.length();
	private Pattern pat=Pattern.compile(imgPathUrl);
	@Autowired
	private MNoticeService mNoticeService;
	
	/**
	 * 
	  * @Description: 跳转到查询页面
	  * @param request
	  * @return
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	@RequestMapping(value = "init")
	@OpLog(logDesc="跳转到查询页面")
	public String init(HttpServletRequest request){
		return "message/noticeInit";
	}
	
	/**
	 * 
	  * @Description: 查询公告
	  * @param request
	  * @param response
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	@OpLog(logDesc="查询提现记录")
	public void list(HttpServletRequest request,HttpServletResponse response,MConditions cons){
		try{
			PageObject po = getPageObject(request);
			po.getCondition().put("startDate", cons.getStartDate());
			po.getCondition().put("endDate", cons.getEndDate());
			
			writeToPage(JSONUtils.toJson(mNoticeService.query(po)), response);
		} catch(Exception e){
			log.error("查询提现单异常", e);
		}
	}
	
	/**
	 * 
	  * @Description: 跳转到编辑页(新增、更新)
	  * @param request
	  * @param noticeId
	  * @return
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	@RequestMapping(value = "toEdit")
	@OpLog(logDesc="跳转到编辑页")
	public String toEdit(HttpServletRequest request,Integer noticeId){
		try{
			if(noticeId!=null){
				request.setAttribute("notice", mNoticeService.findById(noticeId));
			}
		} catch(Exception e){
			log.error("跳转到编辑页异常", e);
		}
		
		return "message/noticeEdit";
	}
	
	/**
	 * 
	  * @Description: 编辑公告(新增、更新)
	  * @param request
	  * @param response
	  * @param session
	  * @param notice
	  * @param content
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	@OpLog(logDesc="编辑公告")
	@RequestMapping(value = "edit")
	@ResponseBody
	public void edit(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			MNoticeModel notice,String content){
		try{
			SysUser user=(SysUser)session.getAttribute("user");
			notice.setDeployBy(user.getRealName());
			content=noticeHandle(session.getServletContext().getRealPath("/")+imgPathLocal,content);
			if(notice.getId()==null){
				mNoticeService.add(notice, content);
			}else{
				mNoticeService.update(notice, content, false);
			}
			writeToPage("{\"code\":0,\"msg\":\"操作成功\"}", response);
		} catch(Exception e){
			log.error("编辑公告异常", e);
			writeToPage("{\"code\":1,\"msg\":\""+e.getMessage()+"\"}", response);
		}
	}
	
	/**
	 * 
	  * @Description: 公告内容处理
	  * @param content
	  * @return
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	private String noticeHandle(String localPath,String content){
		if(content==null){
			return null;
		}
		
        Matcher mat=pat.matcher(content);
        Map<String, String> pathMap=new HashMap<String, String>();
        while(mat.find()){
        	String key=mat.group();
        	if(pathMap.get(key)==null){
        		String fileName=key.substring(imgNameBegin);
            	File file=new File(localPath,fileName);
            	String value=FileUtil.uploadFile(file, fileName);
            	pathMap.put(key, value);
            	file.delete();
        	}
        }
        
        for(String key:pathMap.keySet()){
        	content=content.replaceAll(key, pathMap.get(key));
        }
        
        return content;
	}
}
