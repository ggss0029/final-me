package com.kh.final3.main.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.final3.attendance.model.vo.AttendanceRecord;
import com.kh.final3.board.model.vo.Board;
import com.kh.final3.email.model.vo.Email;


@Repository
public class MainDao {

	//최신 공지 조회 
	public ArrayList<Board> mainNoticeList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainNoticeList");
	}

	//즐겨찾기한 공지 조회 
	public ArrayList<Board> mainNoticeLikedList(SqlSessionTemplate sqlSession, String userId) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainNoticeLikedList", userId);
	}
	
	//최신 메일 조회 
	public ArrayList<Email> mainEmailList(SqlSessionTemplate sqlSession, String userId) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainEmailList", userId);
	}

	//출근 등록 
	public int insertGoToWork(SqlSessionTemplate sqlSession, AttendanceRecord at) {
		return sqlSession.insert("mainMapper.insertGoToWork", at);
	}

	public int updateLeaveToWork(SqlSessionTemplate sqlSession, AttendanceRecord at) {
		return sqlSession.update("mainMapper.updateLeaveToWork", at);
	}


}
