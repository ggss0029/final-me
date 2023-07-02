package com.kh.final3.main.model.dao;

import java.util.ArrayList;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.final3.alert.model.vo.Alert;
import com.kh.final3.approval.model.vo.Approval;
import com.kh.final3.attendance.model.vo.Attendance;
import com.kh.final3.board.model.vo.Board;
import com.kh.final3.member.model.vo.Member;
import com.kh.final3.messenger.model.vo.Messenger;
import com.kh.final3.schedule.model.vo.Schedule;
import com.kh.final3.todo.model.vo.Todo;


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
	
	//최신 쪽지 조회 
	public ArrayList<Messenger> mainMessengerList(SqlSessionTemplate sqlSession, String userId) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainMessengerList", userId);
	}

	//출근 등록 
	public int insertGoToWork(SqlSessionTemplate sqlSession, Attendance at) {
		return sqlSession.insert("mainMapper.insertGoToWork", at);
	}

	//퇴근 등록 
	public int updateLeaveToWork(SqlSessionTemplate sqlSession, Attendance at) {
		return sqlSession.update("mainMapper.updateLeaveToWork", at);
	}

	//임직원 조회 (팀) 
	public ArrayList<Member> mainOthersTeamList(SqlSessionTemplate sqlSession, Member m) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainOthersTeamList",m);
	}

	//임직원 조회 (전체) 
	public ArrayList<Member> mainOthersAllList(SqlSessionTemplate sqlSession, Member m) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainOthersAllList",m);
	}

	//풀캘린더 안에 일정 조회
	public ArrayList<Schedule> mainCalendarList(SqlSessionTemplate sqlSession, String deptCode) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainCalendarList", deptCode);
	}

	//풀캘린더 아래 해달 날짜 일정 조회
	public ArrayList<Schedule> mainDailyEvents(SqlSessionTemplate sqlSession, Map<String, Object> params) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainDailyEvents", params);
	}
	// 근무계획조회
	public Attendance selectLeaveType(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectOne("mainMapper.selectLeaveType", userNo);
	}

	//전자결재 승인 여부 조회 
	public ArrayList<Approval> mainApprovalStatus(SqlSessionTemplate sqlSession, Member m) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainApprovalStatus", m);
	}

	//투두리스트 등록
	public int mainInsertTodo(SqlSessionTemplate sqlSession, Todo td) {
		return sqlSession.insert("mainMapper.mainInsertTodo", td);
	}

	//투두리스트 조회
	public ArrayList<Todo> mainSelectTodoList(SqlSessionTemplate sqlSession, Member m) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainSelectTodoList", m);
	}
	
	//투두리스트 수정
	public int updateTodoList(SqlSessionTemplate sqlSession, Todo td) {
		//System.out.println(sqlSession.update("mainMapper.updateTodoList", td));
		return sqlSession.update("mainMapper.updateTodoList", td);
	}

	//투두리스트 한개 삭제
	public int deleteTodoList(SqlSessionTemplate sqlSession, int todoNo) {
		return sqlSession.update("mainMapper.deleteTodoList", todoNo);
	}

	//투두리스트 전체 삭제
	public int allDeleteTodoList(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("mainMapper.allDeleteTodoList", m);
	}

	public ArrayList<Alert> menuAlertList(SqlSessionTemplate sqlSession, int userNo) {
		return (ArrayList)sqlSession.selectList("mainMapper.menuAlertList", userNo);
	}

	public int menuAlertUpdate(SqlSessionTemplate sqlSession, Alert al) {
		return sqlSession.update("mainMapper.menuAlertUpdate",al);
	}

	public int menuAlertAllDelete(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.delete("mainMapper.menuAlertAllDelete", m);
	}

//	public Attendance userMain(SqlSessionTemplate sqlSession, int userNo) {
//		return (Attendance)sqlSession.selectOne("mainMapper.userMain", userNo);
//	}

	public int insertOnTime(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.insert("mainMapper.insertOnTime", userNo);
	}

	public int insertOffTime(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.insert("mainMapper.insertOffTime", userNo);
	}

	public ArrayList<Attendance> selectOnTime(SqlSessionTemplate sqlSession, int userNo) {
		System.out.println((ArrayList)sqlSession.selectList("mainMapper.selectOnTime", userNo));
		
		return (ArrayList)sqlSession.selectList("mainMapper.selectOnTime", userNo);
	}
	
}
