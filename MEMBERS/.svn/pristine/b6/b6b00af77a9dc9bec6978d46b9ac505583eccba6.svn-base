package com.daboo.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daboo.system.mapper.SysDetailMapper;
import com.daboo.web.context.ObjectFactory;


/**
 * Servlet implementation class HealthCheck
 */
public class HealthCheck extends HttpServlet {
	
	private static final long serialVersionUID = 4744420124680635445L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public HealthCheck() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 转到dopost方法
		 */
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ServletContext servletContext = this.getServletContext(); 	 		
		//查询数据
		try{
			//查询数据
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("dictId", -1);
			ObjectFactory.getBean(SysDetailMapper.class).countByCondition(paraMap);
			
			//正常
			response.getWriter().write("OK");
		}catch(Exception e){
			response.getWriter().write("FAIL");
		}
	}

}
