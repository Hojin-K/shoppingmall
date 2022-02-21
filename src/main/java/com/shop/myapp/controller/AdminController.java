package com.shop.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.myapp.interceptor.Auth;
import com.shop.myapp.interceptor.Auth.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
@Auth(role=Role.ADMIN)
public class AdminController {
	
	@PostMapping("/updateMemberInfo")
	public String updateMemberInfo(String memberId) {
		log.info("updateMemberInfo");
		
		return "관리자 메인페이지";
	}
}
