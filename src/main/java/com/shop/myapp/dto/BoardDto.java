package com.shop.myapp.dto;


public class BoardDto {
	private int BOARD_ID;
	private int ITEM_CODE;
	private String MEMBER_ID;
	private String BOARD_TITLE;
	private String BOARD_CONTENT;
	private int RECOMMEND_ID;
		
	public BoardDto() {
		
	}
	
	public BoardDto(int BOARD_ID, int ITEM_CODE, String MEMBER_ID, String BOARD_TITLE, String BOARD_CONTENT, int RECOMMEND_ID) {
		this.BOARD_ID = BOARD_ID;
		this.ITEM_CODE= ITEM_CODE;
		this.MEMBER_ID = MEMBER_ID;
		this.BOARD_TITLE = BOARD_TITLE;
		this.BOARD_CONTENT = BOARD_CONTENT;
		this.RECOMMEND_ID = RECOMMEND_ID;
	}

	public void setBOARD_ID(int bOARD_ID) {
		BOARD_ID = bOARD_ID;
	}

	public void setITEM_CODE(int iTEM_CODE) {
		ITEM_CODE = iTEM_CODE;
	}

	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}

	public void setBOARD_TITLE(String bOARD_TITLE) {
		BOARD_TITLE = bOARD_TITLE;
	}

	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}

	public void setRidRECOMMEND_ID(int RECOMMEND_ID) {
		this.RECOMMEND_ID = RECOMMEND_ID;
	}
	
}
