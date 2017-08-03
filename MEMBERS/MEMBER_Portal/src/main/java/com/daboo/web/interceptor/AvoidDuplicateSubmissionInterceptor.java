package com.daboo.web.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.user.model.MUserInfoModel;

/**
 * 检查重复提交
 * 
 * @author db
 * 
 */
public class AvoidDuplicateSubmissionInterceptor extends	HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AvoidDuplicateSubmissionInterceptor.class);
	/**
	 * 重复提交token
	 */
	public static final String TOKEN = "com_daboo_token";
	
	/**
	 * 重复提交跳转页面
	 */
	public static final String DEFAULT_TOKEN_MSG_JSP = "RepeatSubmitInfo.jsp";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		MUserInfoModel userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo != null) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.needSaveToken();
				if (needSaveSession) {
					request.getSession(false).setAttribute(TOKEN,UUID.randomUUID().toString());
				}

				boolean needRemoveSession = annotation.needRemoveToken();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						logger.warn("please don't repeat submit,[userInfo = {},url = {}]",
								userInfo, request.getRequestURL());
						request.getRequestDispatcher(request.getContextPath()+DEFAULT_TOKEN_MSG_JSP).forward(request, response);
						//response.sendRedirect(Globals.DEFAULT_TOKEN_MSG_JSP);
						return false;
					}
					request.getSession(false).removeAttribute(TOKEN);
				}
			}
		}
		return true;
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute(TOKEN);
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter(TOKEN);
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}