package com.kh.final3.main.model.service;

import java.util.ArrayList;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.final3.approval.model.vo.Approval;
import com.kh.final3.attendance.model.vo.Attendance;
import com.kh.final3.board.model.vo.Board;
import com.kh.final3.email.model.vo.Email;
import com.kh.final3.main.model.dao.MainDao;
import com.kh.final3.member.model.vo.Member;
import com.kh.final3.schedule.model.vo.Schedule;
import com.kh.final3.todo.model.vo.Todo;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainDao mainDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	//최신 공지 조회 
	@Override
	public ArrayList<Board> mainNoticeList() {
		return mainDao.mainNoticeList(sqlSession);
	}

	//즐겨찾기 공지 조회 
	@Override
	public ArrayList<Board> mainNoticeLikedList(String userId) {
		return mainDao.mainNoticeLikedList(sqlSession, userId);
	}
	
	//최신 메일 조회 
	@Override
	public ArrayList<Email> mainEmailList(String userId) {
		return mainDao.mainEmailList(sqlSession, userId);
	}

	//출근 등록 
	@Override
	public int insertGoToWork(Attendance at) {
		return mainDao.insertGoToWork(sqlSession, at);
	}

	//퇴근 등록 
	@Override
	public int updateLeaveToWork(Attendance at) {
		return mainDao.updateLeaveToWork(sqlSession, at);
	}

	//팀 임직원 조회 
	@Override
	public ArrayList<Member> mainOthersTeamList(Member m) {
		return mainDao.mainOthersTeamList(sqlSession, m);
	}

	//임직원 조회 (전체) 
	@Override
	public ArrayList<Member> mainOthersAllList(Member m) {
		return mainDao.mainOthersAllList(sqlSession, m);
	}

	//풀캘린더 안에 전체 일정 조회 
	@Override
	public ArrayList<Schedule> mainCalendarList(String deptCode) {
		return mainDao.mainCalendarList(sqlSession, deptCode);
	}

	//선택한 날짜 일정 조회 
	@Override
	public ArrayList<Schedule> mainDailyEvents(Map<String, Object> params) {
		return mainDao.mainDailyEvents(sqlSession, params);
	}

	//전자결재 리스트 조회 
	@Override
	public ArrayList<Approval> mainApprovalStatus(Member m) {
		return mainDao.mainApprovalStatus(sqlSession, m);
	}

	@Override
	public int mainInsertTodo(Todo td) {
		return mainDao.mainInsertTodo(sqlSession,td);
	}

	@Override
	public ArrayList<Todo> mainSelectTodoList(Member m) {
		return mainDao.mainSelectTodoList(sqlSession, m);
	}


}
