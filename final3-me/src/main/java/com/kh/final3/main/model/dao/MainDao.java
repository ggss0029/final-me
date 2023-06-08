package com.kh.final3.main.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.final3.board.model.vo.Board;


@Repository
public class MainDao {

	public ArrayList<Board> mainNoticeList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("mainMapper.mainNoticeList");
	}

}
