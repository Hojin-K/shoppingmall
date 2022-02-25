package com.shop.myapp.dto;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
	private String memberId;
	private String memberPwd; 
	private LinkedList<String> memberLevel;
	private String memberLevelToString;
	private String memberName;
	private String memberEmail;
	private String memberAddress;
	private String memberTel;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate memberBirth;
	private String businessRegistrationNo;
	private String businessName;
	private String businessInfo;
	private String isDelete;
    private String detailAddress;
    private String address;
	
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
        this.memberAddress = this.address +" "+this.detailAddress;
    }
    
    public void setMemberLevel(String memberLevel) {
    	this.memberLevelToString = memberLevel;
    	List<String> list  =  Arrays.asList(memberLevel.trim().split(","));    		
    	LinkedList<String> levels = new LinkedList<>(list);
    	System.out.println("객체 ->"+levels.toString());
    	System.out.println("ToString -> "+memberLevelToString);
    	this.memberLevel = levels;
    }
}
