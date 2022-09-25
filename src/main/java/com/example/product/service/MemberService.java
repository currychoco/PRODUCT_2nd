package com.example.product.service;

import org.springframework.stereotype.Service;

import com.example.product.domain.Member;
import com.example.product.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public void addMember(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
	}
	
	public Member checkLogin(String id, String password) {
		return memberRepository.findByIdAndPassword(id, password);
	}
	
	private void validateDuplicateMember(Member member) {
		Member m1 = memberRepository.findById(member.getId());
		if(m1 != null) {
			throw new IllegalStateException("이미 존재하는 아이디입니다.");
		}
		Member m2 = memberRepository.findByName(member.getName());
		if(m2 != null) {
			throw new IllegalStateException("이미 존재하는 닉네임입니다.");
		}
	}
}
