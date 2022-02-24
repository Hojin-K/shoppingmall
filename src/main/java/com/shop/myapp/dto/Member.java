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
	private String memberLevel;
	private LinkedList<String> levelList;
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
    
    public LinkedList<String> getLevelList() {
    	List<String> list  =  Arrays.asList(memberLevel.trim().split(","));    		
    	LinkedList<String> levels = new LinkedList<>(list);
    	return levels;
    }
    
    /*public void setMemberLevel(List<String> levelList) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < levelList.size(); i++) {
			sb.append(levelList.get(i));
			if(i != (levelList.size()-1)){
				sb.append(",");
			}
		}
    	this.memberLevel = sb.toString();
    	System.out.println(this.memberLevel);
    }*/
    public void listToString(LinkedList<String> list) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if(i != (list.size()-1)){
				sb.append(",");
			}
		}
    	this.memberLevel = sb.toString();
    	System.out.println(this.memberLevel);
    }
}
