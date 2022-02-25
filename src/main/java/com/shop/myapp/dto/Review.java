package com.shop.myapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Review {
	
	private int reviewCode;
	private int itemCode;
	private String memberId;
	private String reviewContent;

	@Builder
	public Review(int reviewCode, int itemCode, String memberId, String reviewContent) {
		this.reviewCode = reviewCode;
		this.itemCode = itemCode;
		this.memberId = memberId;
		this.reviewContent = reviewContent;
	}
}
	    



