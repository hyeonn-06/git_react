package com.webjjang.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webjjang.board.service.BoardService;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("/board")
@Log4j2
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/list.do")
	public ResponseEntity<Map<String,Object>> list(PageObject pageObject) throws Exception {
		log.info("----- BoardController list() -----");
		Map<String, Object> map = new HashMap<>();
		map.put("list", service.list(pageObject));
		map.put("pageObject", pageObject);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/view.do")
	public ResponseEntity<BoardVO> view(@RequestParam("no") Long no, @RequestParam("inc")  int inc) {
		log.info("----- BoardController view() -----");
		BoardVO vo = service.view(no, inc);
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@PostMapping("/write.do")
	public ResponseEntity<String> write(@RequestBody BoardVO vo){
		service.write(vo);
		return new ResponseEntity<>("일반 게시판 글 등록 성공!", HttpStatus.OK);
	}
	
	@PostMapping("/update.do")
	public ResponseEntity<String> update(@RequestBody BoardVO vo){
		service.update(vo);
		return new ResponseEntity<>("일반 게시판 글 수정 성공!", HttpStatus.OK);
	}
	
	@PostMapping("/delete.do")
	public ResponseEntity<String> delete(@RequestBody BoardVO vo){
		service.delete(vo);
		return new ResponseEntity<>("일반 게시판 글 삭제 성공!", HttpStatus.OK);
	}
}
