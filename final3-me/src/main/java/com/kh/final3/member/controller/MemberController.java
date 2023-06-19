package com.kh.final3.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.final3.member.model.service.MemberService;
import com.kh.final3.member.model.vo.Member;

@Controller // Controller타입의 어노테이션을 붙여주면 빈 스캐닝을 통해서 자동으로 bean 등록을 해준다.
public class MemberController {
	@Autowired
	private MemberService memberService;

//	@Autowired
//	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {

		Member loginUser = memberService.loginMember(m);
		System.out.println(loginUser);

		if (loginUser == null) {
			mv.addObject("errorMsg", "로그인 실패");
			mv.setViewName("common/errorPage");
		} else {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("/main/userMain");
		}
		return mv;
	}

	// 로그아웃
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}

	// 회원가입
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "member/enrollForm";
	}

	// 회원가입 메소드
//	@RequestMapping("insert.me")
//	public String insertMember(Member m, HttpSession session, Model model) {
//		// 암호화 작업(암호문 만들기) -> 위에서 bcryptPasswordEncoder 선언해주기
//		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
//		// 같은 비밀번호를 쳐도 암호문이 매번 다르게 나옴
////		System.out.println("암호화된 패스워드 : " + encPwd);
//		m.setUserPwd(encPwd);
//		// 회원가입 구현
//		int result = memberService.insertMember(m);
//		// 성공시 - 메인페이지로 url 재요청
//		if (result > 0) {
//			// 성공 메세지 세션에 담아서 띄우기
//			session.setAttribute("alertMsg", "회원가입 성공");
//			return "redirect:/";
//		} else {
//			// 실패시 - 에러페이지로 에러메세지 담아서 포워딩하기
//			model.addAttribute("errorMsg", "회원가입 실패");
//			return "common/errorPage";
//		}
//	}

	@ResponseBody
	@RequestMapping(value = "checkId.me")
	public String checkIdMember(String checkId) {
		int count = memberService.checkIdMember(checkId);
		if (count > 0) { // 조회결과가 있다(중복)
			return "NN";
		} else { // 조회 결과가 없음(사용가능)
			return "NY";
		}

		// return (count>0) ? "NN":"NY"; //삼항연산자로 해도됨
	}

}
