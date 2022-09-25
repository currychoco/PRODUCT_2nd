package com.example.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		String ip = (String)session.getAttribute("ip");
		
		String currentIp = request.getLocalAddr();
		
		if(id != null && !currentIp.equals(ip)) {
			session.invalidate();
		}
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		return "home";
	}
	
}
