package com.office.library.admin.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AdminMemberLoginInterceptor 
		implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session != null) {
			Object object = session.getAttribute("loginedAdminMemberVo");
			if(object != null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath()+"/admin/member/loginForm");
		
		
		return false;
	}
	
}
