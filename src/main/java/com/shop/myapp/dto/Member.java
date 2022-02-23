package com.shop.myapp.dto;


import java.time.LocalDate;
import java.util.LinkedList;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
	private String memberId;
	private String memberPwd; 
	private String memberLevel;
	private String memberName;
	private String memberAddress;
	private String memberTel;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate memberBirth;
	private String businessRegistrationNo;
	private String businessName;
    private String detailAddress;
    private String address;
	
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
        this.memberAddress = this.address +" "+this.detailAddress;
    }
}
