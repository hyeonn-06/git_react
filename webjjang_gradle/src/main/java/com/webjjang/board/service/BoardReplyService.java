package com.webjjang.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webjjang.board.mapper.BoardReplyMapper;
import com.webjjang.board.vo.BoardReplyVO;
import com.webjjang.util.page.PageObject;

@Service
public class BoardReplyService {
	
	@Autowired
	private BoardReplyMapper mapper;
	
	// list
	public List<BoardReplyVO> list(PageObject pageObject, Long no){
		pageObject.setTotalRow(mapper.getTotalRow(no));
		return mapper.list(pageObject, no);
	}
	// write
	public Integer write(BoardReplyVO vo){
		return mapper.write(vo);
	}
	// update
	public Integer update(BoardReplyVO vo){
		return mapper.update(vo);
	}
	// delete
	public Integer delete(BoardReplyVO vo){
		return mapper.delete(vo);
	}
}
