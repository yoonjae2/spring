package com.office.library.user.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserMemberLoginInterceptor 
		implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session != null) {
			Object object = session.getAttribute("loginedUserMemberVo");
			if(object != null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath()+"/user/member/loginForm");
		return false;
	}

	
	
}
