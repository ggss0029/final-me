package com.kh.final3.member.model.service;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.final3.member.model.dao.MemberDao;
import com.kh.final3.member.model.vo.Member;

//interface에는 객체를 생성할 수 없으니 실질적으로 일하는 MemberServiceImpl에 @Service 어노테이션을 사용해준다. 
//(@Component로도 등록시켜도 되지만 각자의 역할에 맞춰서 좀 더 구체화된 어노테이션 부여)
@Service
public class MemberServiceImpl implements MemberService {
	
	//기존 방식 
	//private MemberDao memberDao = new MemberDao();
	
	//spring 방식
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Member loginMember(Member m) {
		
		Member loginUser = memberDao.loginMember(sqlSession, m);
		
		return loginUser;
	}

	//회원가입 메소드
//	@Override
//	public int insertMember(Member m) {
//		int result = memberDao.insertMember(sqlSession, m);
//		
//		return result;
//	}

	//아이디 중복체크
	@Override
	public int checkIdMember(String checkId) {
		return memberDao.checkIdMember(sqlSession, checkId);
	}

}
