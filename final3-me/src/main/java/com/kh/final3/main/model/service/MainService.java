package com.kh.final3.main.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import com.kh.final3.approval.model.vo.Approval;
import com.kh.final3.approval.model.vo.ApprovalDoc;
import com.kh.final3.attendance.model.vo.Attendance;
import com.kh.final3.board.model.vo.Board;
import com.kh.final3.email.model.vo.Email;
import com.kh.final3.member.model.vo.Member;
import com.kh.final3.schedule.model.vo.Schedule;
import com.kh.final3.todo.model.vo.Todo;



public interface MainService {

	//최신 공지사항 조회 
	ArrayList<Board> mainNoticeList();
	
	//즐겨찾기한 공지사항 조회 
	ArrayList<Board> mainNoticeLikedList(String userId);

	//최신 메일 조회 
	ArrayList<Email> mainEmailList(String userId);

	//출근 등록 
	int insertGoToWork(Attendance at);

	//퇴근 등록 
	int updateLeaveToWork(Attendance at);

	//임직원 조회 (팀) 
	ArrayList<Member> mainOthersTeamList(Member m);

	//임직원 조회 (전체) 
	ArrayList<Member> mainOthersAllList(Member m);

	//전체 일정 조회 
	ArrayList<Schedule> mainCalendarList(String deptCode);

	//선택한 날짜 일정 조회 
	ArrayList<Schedule> mainDailyEvents(Map<String, Object> params);

	ArrayList<Approval> mainApprovalStatus(Member m);

	int mainInsertTodo(Todo td);

	ArrayList<Todo> mainSelectTodoList(Member m);


}
