package com.kh.final3.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.final3.member.model.vo.Member;

//Repository : "저장소"라는 뜻, 주로 DB와 관련한 작업을 수행하는 역할
@Repository
public class MemberDao {

	//로그인 메소드
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember",m);
	}

	//회원가입
//	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
//		return sqlSession.insert("memberMapper.insertMember",m);
//	}

	//아이디 중복체크
	public int checkIdMember(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.checkIdMember",checkId);
	}

}
