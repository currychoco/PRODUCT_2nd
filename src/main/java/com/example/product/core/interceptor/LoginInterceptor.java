package com.example.product.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null) {
			System.out.println("로그인 실패");
			response.sendRedirect("/member/login");
			return false;
		}
		return true;
	}
	
}
