package com.webjjang.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webjjang.board.mapper.BoardMapper;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.page.PageObject;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> list(PageObject pageObject){
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		return mapper.list(pageObject);
	}
	
	public BoardVO view(Long no, int inc) {
		if(inc == 1) mapper.increase(no);
		return mapper.view(no);
	}
	
	public Integer write(BoardVO vo) {
		return mapper.write(vo);
	}
	
	public Integer update(BoardVO vo) {
		return mapper.update(vo);
	}

	public Integer delete(BoardVO vo) {
		return mapper.delete(vo);
	}
}
