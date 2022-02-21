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
	
	/*public void setMemberBirth(String memberBirth) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println(memberBirth);
		this.memberBirth = LocalDate.parse(memberBirth, format);
		System.out.println(this.memberBirth);
	}*/
	
	/*public String getMemberBirth() {
		
		String birth = this.memberBirth.toString();
		log.info("birth : {}", birth);
		return birth;
	}*/
}
