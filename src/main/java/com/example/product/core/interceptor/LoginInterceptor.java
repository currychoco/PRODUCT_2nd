package com.example.product.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		if(modelAndView != null) {
			HttpSession session = request.getSession();
			modelAndView.addObject("name", session.getAttribute("name"));
		}
		
	}
	
}
