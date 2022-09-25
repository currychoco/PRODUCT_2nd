package com.example.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.product.domain.Member;
import com.example.product.dto.MemberJoinDto;
import com.example.product.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping(value = "/member/join")
	public String joinForm() {
		return "member/memberJoin";
	}
	
	@PostMapping(value = "/member/join")
	public String join(MemberJoinDto joinDto) {
		Member member = new Member(joinDto.getId(), joinDto.getPassword(), joinDto.getName());
		memberService.addMember(member);
		return "redirect:/member/login";
	}
	
	@GetMapping(value="member/login")
	public String loginForm() {
		return "member/memberLogin";
	}
	
	@PostMapping(value="member/login")
	public String login(MemberJoinDto joinDto, HttpServletRequest request) {
		Member member = memberService.checkLogin(joinDto.getId(), joinDto.getPassword());
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("id", member.getId());
			session.setAttribute("name", member.getName());
			session.setAttribute("ip", request.getLocalAddr());
			return "redirect:/";
		}else {
			return "redirect:/member/login";
		}
	}
	
	@GetMapping(value = "member/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/member/login";
	}
}
