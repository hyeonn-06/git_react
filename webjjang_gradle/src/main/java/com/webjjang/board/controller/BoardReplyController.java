package com.webjjang.board.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webjjang.board.service.BoardReplyService;
import com.webjjang.board.vo.BoardReplyVO;
import com.webjjang.util.page.PageObject;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

// Ajax를 통해서 데이터만 전달하는 객체 - Rest 데이터를 주고 받는 RestController
@RestController
@RequestMapping("/boardreply")
@Log4j2
public class BoardReplyController {
	
	@Autowired
	private BoardReplyService service;
	
	// 댓글 리스트
	// 받아야할 데이터 : 페이지, 게시판 글번호
	// 전달 결과 데이터 - 페이지에 맞는 댓글 리스트 데이터, 페이지 정보, 아이디
	@GetMapping(value = "/list.do",
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<Map<String,Object>> list(PageObject pageObject, Long no, HttpSession session){
		log.info("no=" + no + ", pageObject=" + pageObject);
		List<BoardReplyVO> list = service.list(pageObject, no);
		
		// 넘겨 줄 데이터를 Map으로 만든다.
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		map.put("id", "test");
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	// 댓글 등록
	// 받아야할 데이터 : 댓글 내용, 아이디(session)
	@PostMapping(value = "/write.do",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	public ResponseEntity<String> write(@RequestBody BoardReplyVO vo, HttpSession session){
		vo.setId("test");
		service.write(vo);
		return new ResponseEntity<>("댓글이 등록되었습니다.", HttpStatus.OK);
	}
	// 댓글 수정
	// 받아야할 데이터 : 댓글 번호, 댓글 내용, 아이디(session)
	@PostMapping(value = "/update.do",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	public ResponseEntity<String> update(@RequestBody BoardReplyVO vo, HttpSession session){
		vo.setId("test");
		Integer result = service.update(vo);
		return result == 1 ? new ResponseEntity<>("댓글이 수정되었습니다.", HttpStatus.OK)
		:  new ResponseEntity<>("댓글 수정에 필요한 정보를 확인해주세요.", HttpStatus.PRECONDITION_FAILED);
	}
	// 댓글 삭제
	// 받아야할 데이터 : 댓글 내용, 아이디(session)
	@GetMapping(value="/delete.do",
			produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	public ResponseEntity<String> delete(BoardReplyVO vo, HttpSession session){
		vo.setId("test");
		Integer result = service.delete(vo);
		
		return result == 1 ?  new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.OK)
		: new ResponseEntity<>("댓글 삭제에 필요한 정보를 확인해주세요.", HttpStatus.PRECONDITION_FAILED);
	}
}
