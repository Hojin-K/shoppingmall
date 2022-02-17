package com.shop.myapp.dto;


public class BoardDto {
	private int board_id;
	private int item_code;
	private String member_id;
	private String board_title;
	private String board_content;
	private int recommend_id;
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}
	public BoardDto(int board_id, int item_code, String member_id, String board_title, String board_content,
			int recommend_id) {
		
		this.board_id = board_id;
		this.item_code = item_code;
		this.member_id = member_id;
		this.board_title = board_title;
		this.board_content = board_content;
		this.recommend_id = recommend_id;
	}
	
	public int getBoard_id() {
		return board_id;
	}
	public int getItem_code() {
		return item_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public String getBoard_title() {
		return board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public int getRecommend_id() {
		return recommend_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public void setItem_code(int item_code) {
		this.item_code = item_code;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public void setRecommend_id(int recommend_id) {
		this.recommend_id = recommend_id;
	}

}
