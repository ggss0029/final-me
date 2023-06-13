package com.kh.final3.main.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.kh.final3.attendance.model.vo.AttendanceRecord;
import com.kh.final3.board.model.vo.Board;
import com.kh.final3.email.model.vo.Email;
import com.kh.final3.main.model.service.MainService;
import com.kh.final3.member.model.vo.Member;

@RequestMapping(value={"/member","/admin"})
@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;

	//사용자 메인페이지 이동 
	@RequestMapping("home.ma")
	public String MainHome() {
		return "main/userMain";
	}
	
	//관리자 메인페이지 이동 
	@RequestMapping("adminHome.ma")
	public String adminMain() {
		return "main/adminMain";
	}
	
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
		System.out.println(userId);
		ArrayList<Board> mainNoticeLiked = mainService.mainNoticeLikedList(userId);
		System.out.println(mainNoticeLiked);
		return new Gson().toJson(mainNoticeLiked);
	}
	
	
	//메일 불러오기
	@ResponseBody
	@RequestMapping(value="mainEmailList.ma", produces="application/json; charseet=UTF-8")
	public String mainEmailList(HttpSession session) {
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
//		System.out.println(userId);
		ArrayList<Email> mainEmail = mainService.mainEmailList(userId);
//		System.out.println(mainEmail);
		
		return new Gson().toJson(mainEmail);
	}
	
	//출근등록 
//	@PostMapping("insertGo.ma")
	@RequestMapping("insertGo.ma")
	//@RequestMapping(value="insertGo.ma", method=RequestMethod.POST)
	public String insertGoToWork(AttendanceRecord at, HttpSession session, RedirectAttributes rttr) {
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		
		at.setUserNo(userNo);
		
		int result = mainService.insertGoToWork(at);
		
		if(result > 0) {
			rttr.addFlashAttribute("onTime",at.getOnTime());
			session.setAttribute("alertMsg", "출근 성공!");
		}else {
			session.setAttribute("alertMsg", "출근 실패! ");
		}
		return "redirect:/member/home.ma";
	}
	
	//퇴근 등록 
	@RequestMapping("insertLeave.ma")
	//@RequestMapping(value="insertGo.ma", method=RequestMethod.POST)
	public String updateLeaveToWork(AttendanceRecord at, HttpSession session, RedirectAttributes rttr) {
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		
		at.setUserNo(userNo);
		
		int result = mainService.updateLeaveToWork(at);
		
		if(result > 0) {
			rttr.addFlashAttribute("offTime",at.getOffTime());
			session.setAttribute("alertMsg", "퇴근 성공!");
		}else {
			session.setAttribute("alertMsg", "퇴근 실패! ");
		}
		return "redirect:/member/home.ma";
	}
}
