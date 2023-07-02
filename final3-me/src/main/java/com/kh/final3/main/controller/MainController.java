package com.kh.final3.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.kh.final3.alert.model.vo.Alert;
import com.kh.final3.approval.model.vo.Approval;
import com.kh.final3.attendance.model.vo.Attendance;
import com.kh.final3.board.model.vo.Board;
import com.kh.final3.main.model.service.MainService;
import com.kh.final3.member.model.vo.Member;
import com.kh.final3.messenger.model.vo.Messenger;
import com.kh.final3.schedule.model.vo.Schedule;
import com.kh.final3.todo.model.vo.Todo;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;

	@Autowired
	private Todo todo;

	//사용자 메인페이지 이동 
	@RequestMapping("home.ma")
	public String MainHome() {
		return "main/userMain";
	}
	
//	@RequestMapping("userMain.ma")
//	public String userMain(HttpSession session, Model model) {
//	    Member loginUser = (Member) session.getAttribute("loginUser");
//	    int userNo = loginUser.getUserNo();
//	    
//	    Attendance att = mainService.userMain(userNo);
//	    System.out.println(att);
//	    if (att != null) {
//	        model.addAttribute("att", att);
//	    } else {
//	        session.setAttribute("alertMsg", "출퇴근 기록이 없습니다.");
//	    }
//	    
//	    return "main/userMain";
//	}
	
	//최근 공지 불러오기 
	@ResponseBody
	@RequestMapping(value="mainNoticeList.ma", produces="application/json; charset=UTF-8")
	public String mainNoticeList() {
		ArrayList<Board> mainNotice = mainService.mainNoticeList();
		return new Gson().toJson(mainNotice);
	}
	
	//즐겨찾기  공지 불러오기 
	@ResponseBody
	@RequestMapping(value="mainNoticeLiked.ma", produces="application/json; charset=UTF-8")
	public String mainNoticeLikedList(HttpSession session) {
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
		//System.out.println(userId);
		ArrayList<Board> mainNoticeLiked = mainService.mainNoticeLikedList(userId);
		//System.out.println(mainNoticeLiked);
		return new Gson().toJson(mainNoticeLiked);
	}
	
	
	//쪽지 불러오기
	@ResponseBody
	@RequestMapping(value="mainMessengerList.ma", produces="application/json; charseet=UTF-8")
	public String mainMessengerList(HttpSession session) {
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
//		System.out.println(userId);
		ArrayList<Messenger> mainMessenger = mainService.mainMessengerList(userId);
//		System.out.println(mainEmail);
		
		return new Gson().toJson(mainMessenger);
	}
	
	//출근등록 
//	@PostMapping("insertGo.ma")
//	//@RequestMapping("insertGo.ma")
//	//@RequestMapping(value="insertGo.ma", method=RequestMethod.POST)
//	public String insertGoToWork(Attendance at, HttpSession session, RedirectAttributes rttr) {
//		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
//		// 근무계획 조회
//		Attendance at2 = mainService.selectLeaveType(userNo);
//		
//		if(at2 == null) { // 근무계획 없으면
//			at.setUserNo(userNo);
//		}else { // 근무계획 있으면
//			at.setUserNo(userNo);
//			at.setLeaveType(at2.getLeaveType());
//		}
//		int result = mainService.insertGoToWork(at);
//
//		if (result > 0) {
//			rttr.addFlashAttribute("onTime", at.getOnTime());
//			session.setAttribute("alertMsg", "출근 성공!");
//		} else {
//			session.setAttribute("alertMsg", "출근 실패! ");
//		}
//
//		return "redirect:/member/mainPage.me";
//	}
//	
//	//퇴근 등록 
//	//@RequestMapping("insertLeave.ma")
//	@RequestMapping(value="insertLeave.ma", method=RequestMethod.POST)
//	public String updateLeaveToWork(Attendance at, HttpSession session, RedirectAttributes rttr) {
//		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
//		
//		at.setUserNo(userNo);
//		
//		int result = mainService.updateLeaveToWork(at);
//		
//		if(result > 0) {
//			rttr.addFlashAttribute("onTime",at.getOnTime());
//			session.setAttribute("alertMsg", "퇴근 성공!");
//		}else {
//			session.setAttribute("alertMsg", "퇴근 실패! ");
//		}
//		return "redirect:/member/mainPage.me";
//	}
	
	//임직원조회(Team)
	@ResponseBody
	@RequestMapping(value="mainOthersTeamList.ma", produces="application/json; charset=UTF-8" )
	public String mainOthersTeamList(HttpSession session) {
		Member m = ((Member)session.getAttribute("loginUser"));

		ArrayList<Member> mainMemberTeam = mainService.mainOthersTeamList(m);
		
		return new Gson().toJson(mainMemberTeam);
	}
	
	//임직원조회(ALL)
	@ResponseBody
	@RequestMapping(value="mainOthersAllList.ma", produces="application/json; charset=UTF-8" )
	public String mainOthersAllList(HttpSession session) {
		Member m = ((Member)session.getAttribute("loginUser"));

		ArrayList<Member> mainMemberTeam = mainService.mainOthersAllList(m);
			
		return new Gson().toJson(mainMemberTeam);
	}
	
	//풀 캘린더에서 일정조회 
	@ResponseBody
	@RequestMapping(value="mainCalendar.ma", produces="application/json; charset=UTF-8")
	public String mainCalendarList(HttpSession session) {
		String deptCode = ((Member)session.getAttribute("loginUser")).getDeptCode();
		
		ArrayList<Schedule> list = mainService.mainCalendarList(deptCode);
		return new Gson().toJson(list);
	}
	
	//선택한 날짜 일정 조회 
	@ResponseBody
	@RequestMapping(value="mainDailyEvents.ma", method = RequestMethod.POST)
	public ArrayList<Schedule> mainDailyEvents(HttpSession session, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("date") int date) {
		String deptCode = ((Member)session.getAttribute("loginUser")).getDeptCode();
		Map<String, Object> params = new HashMap<>();
		params.put("year", year);
		params.put("month", month);
		params.put("date", date);
		params.put("deptCode", deptCode);
		
		ArrayList<Schedule> events = mainService.mainDailyEvents(params);
		//System.out.println(events);
		return events;
	}
	
	//전자결재 리스트 조회 
	@ResponseBody
	@RequestMapping(value="mainApprovalStatus.ma", produces="application/json; charset=UTF-8")
	public String mainApprovalStatus(HttpSession session) {
		Member m = ((Member)session.getAttribute("loginUser"));
		
		ArrayList<Approval> aList = mainService.mainApprovalStatus(m);
		//System.out.println(aList);
		return new Gson().toJson(aList);
	}
	
	//투두리스트 입력
	@ResponseBody
	@RequestMapping(value="mainInsertTodo.ma", method = RequestMethod.POST)
	public String mainInsertTodo(HttpSession session, String todoContent) {
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		//System.out.println(userNo);
		//Todo td = new Todo();
		todo.setUserNo(userNo);
		todo.setTodoContent(todoContent);
		
		int result = mainService.mainInsertTodo(todo);
		
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	//투두 리스트 조회 
	@ResponseBody
	@RequestMapping(value = "selectTodoList.ma", produces = "application/json; charset=UTF-8")
	public String mainSelectTodoList(HttpSession session, int userNo) {
		Member m = ((Member)session.getAttribute("loginUser"));
		m.setUserNo(userNo);
		
		ArrayList<Todo> list = mainService.mainSelectTodoList(m);
		//System.out.println(list);
		return new Gson().toJson(list);
	}
	
	//투두 수정 
	@ResponseBody
	@RequestMapping(value = "updateTodoList.ma", method = RequestMethod.POST)
	public String updateTodoList(int todoNo, String status) {
		todo.setTodoNo(todoNo);
		todo.setStatus(status);
		
		int result = mainService.updateTodoList(todo);
		//System.out.println(result);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	//투두 리스트 한개 삭제 
	@ResponseBody
	@RequestMapping(value = "deleteTodoList.ma", method = RequestMethod.POST)
	public String deleteTodoList(int todoNo) {
		//System.out.println(todoNo);
		int result = mainService.deleteTodoList(todoNo);
		
		return (result > 0) ? "success" : "fail";
	}
	
	//투두 리스트 모두 삭제 
	@ResponseBody
	@RequestMapping(value = "allDeleteTodoList.ma", method = RequestMethod.POST)
	public String allDeleteTodoList(HttpSession session, int userNo) {
		Member m = ((Member)session.getAttribute("loginUser"));
		m.setUserNo(userNo);
		
		int result = mainService.allDeleteTodoList(m);
		
		return (result > 0) ? "success" : "fail";
	}
	
	//알림 전체 조회 
	@ResponseBody
	@RequestMapping(value = "menuAlertList.ma", produces = "application/json; charset=UTF-8")
	public String menuAlertList(HttpSession session) {
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
//		System.out.println(userId);
		ArrayList<Alert> mainAlert = mainService.menuAlertList(userNo);
//		System.out.println(mainEmail);
		return new Gson().toJson(mainAlert);
	}
	
	//알림 a태그 선택시 상태 변경(읽음 처리)
	@ResponseBody
	@RequestMapping(value = "menuAlertUpdate.ma", method = RequestMethod.POST)
	public String menuAlertUpdate(int alertNo, String status) {
		Alert al = new Alert();
		al.setAlertNo(alertNo);
		al.setStatus(status);
		
		int result = mainService.menuAlertUpdate(al);
		//System.out.println(result);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	//알림 전체 삭제
	@ResponseBody
	@RequestMapping(value = "menuAlertAllDelete.ma", method = RequestMethod.POST)
	public String menuAlertAllDelete(HttpSession session, int userNo) {
		Member m = ((Member)session.getAttribute("loginUser"));
		m.setUserNo(userNo);
		
		int result = mainService.menuAlertAllDelete(m);
		
		return (result > 0) ? "success" : "fail";
	}
	
	//출퇴근 조회
	@ResponseBody
	@RequestMapping(value = "mainSelectOnTime.ma", produces = "application/json; charset=UTF-8")
	public String selectOnTime(HttpSession session) {
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		System.out.println(userNo);
		ArrayList<Attendance> atList = mainService.selectOnTime(userNo);

		System.out.println(atList);
		return new Gson().toJson(atList);
	}
	
	//출근 등록 
	@ResponseBody
	@RequestMapping(value = "insertOnTime.ma", method = RequestMethod.POST)
	public String insertOnTime(HttpSession session) {
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();

		int result = mainService.insertOnTime(userNo);
		System.out.println(result);
		
		return (result > 0) ? "success" : "fail";
	}
	
	//퇴근 등록
	@ResponseBody
	@RequestMapping(value = "insertOffTime.ma", method = RequestMethod.POST)
	public String insertOffTime(HttpSession session) {
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();

		int result = mainService.insertOffTime(userNo);
		System.out.println(result);
		
		return (result > 0) ? "success" : "fail";
	}

//	@RequestMapping(value="insertMsg.ma", method=RequestMethod.GET)
//	public String msgEnrollForm(@RequestParam("userId") String userId, @RequestParam("userName") String userName) {
//		System.out.println(userId);
//		return "messenger/msgEnrollForm";
//	}
}
