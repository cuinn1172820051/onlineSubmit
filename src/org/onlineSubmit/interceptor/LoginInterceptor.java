package org.onlineSubmit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录过滤拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
	//请求完成之后
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	//请求正在发生
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	//请求完成之前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer url = request.getRequestURL();
		System.out.print("进入拦截器,url="+url);
		Object user =  request.getSession().getAttribute("user");
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/system/login");
			return false;
		}
		return true;
	}
	
}
