package com.webjjang.board.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardReplyVO {
	private Long rno;
	private Long no;
	private String content;
	private String id;
	private Date writeDate;
}
