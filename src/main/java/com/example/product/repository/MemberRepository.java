package com.example.product.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.example.product.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
private final SqlSessionTemplate sqlSession;
	
	public void save(Member member) {
		sqlSession.insert("member.save", member);
	}
	
	public Member findByName(String name) {
		return sqlSession.selectOne("member.findByName", name);
	}
	
	public Member findById(String id) {
		return sqlSession.selectOne("member.findById", id);
	}
	
	public Member findByIdAndPassword(String id, String password) {
		Member member = new Member(id, password, null);
		return sqlSession.selectOne("member.findByIdAndPassword", member);
	}
}
