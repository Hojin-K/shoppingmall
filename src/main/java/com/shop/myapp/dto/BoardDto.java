package com.shop.myapp.dto;


public class BoardDto {
	private int BOARD_ID;
	private int ITEM_CODE;
	private String MEMBER_ID;
	private String BOARD_TITLE;
	private String BOARD_CONTENT;
	private int RECOMMEND_ID;
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int bOARD_ID, int iTEM_CODE, String mEMBER_ID, String bOARD_TITLE, String bOARD_CONTENT,
			int rECOMMEND_ID) {
//		System.out.println("김형철"+iTEM_CODE);
		BOARD_ID = bOARD_ID;
		ITEM_CODE = iTEM_CODE;
		MEMBER_ID = mEMBER_ID;
		BOARD_TITLE = bOARD_TITLE;
		BOARD_CONTENT = bOARD_CONTENT;
		RECOMMEND_ID = rECOMMEND_ID;
	}

	public int getBOARD_ID() {
		return BOARD_ID;
	}

	public int getITEM_CODE() {
		return ITEM_CODE;
	}

	public String getMEMBER_ID() {
		return MEMBER_ID;
	}

	public String getBOARD_TITLE() {
		return BOARD_TITLE;
	}

	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}

	public int getRECOMMEND_ID() {
		return RECOMMEND_ID;
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

	public void setRECOMMEND_ID(int rECOMMEND_ID) {
		RECOMMEND_ID = rECOMMEND_ID;
	}

	}
	