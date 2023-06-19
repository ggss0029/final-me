package com.kh.final3.member.model.service;

import com.kh.final3.member.model.vo.Member;

//interface는 객체 생성 불가능 그러므로 memberServiceImpl가 있는 것이다.
public interface MemberService {
	
	//로그인용 메소드(select)
	Member loginMember(Member m);
	
	//회원가입(insert)
//	int insertMember(Member m);
	
	//아이디 중복 체크
	int checkIdMember(String checkId);

	
}
