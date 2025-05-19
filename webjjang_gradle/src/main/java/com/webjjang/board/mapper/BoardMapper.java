package com.webjjang.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.page.PageObject;

@Mapper
public interface BoardMapper {
	public List<BoardVO> list(PageObject pageObject);
	public Long getTotalRow(PageObject pageObject);
	public Integer increase(Long no);
	public BoardVO view(Long no);
	public Integer write(BoardVO vo);
	public Integer update(BoardVO vo);;
	public Integer delete(BoardVO vo);
}
