package com.kh.final3.main.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.final3.board.model.vo.Board;
import com.kh.final3.main.model.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;

	@RequestMapping("home.ma")
	public String MainHome() {
		return "main/userMain";
	}
	
	@ResponseBody
	@RequestMapping(value="mainNoticeList.bo", produces="application/json; charset=UTF-8")
	public String mainNoticeList() {
		ArrayList<Board> mainNotice = mainService.mainNoticeList();
		return new Gson().toJson(mainNotice);
	}
}
