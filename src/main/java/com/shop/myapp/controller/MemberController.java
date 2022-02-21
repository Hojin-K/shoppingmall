package com.shop.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.myapp.dto.Member;
import com.shop.myapp.service.AuthService;
import com.shop.myapp.service.AuthServiceImpl;
import com.shop.myapp.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    public MemberController(MemberService memberService, AuthServiceImpl authService) {
        this.memberService = memberService;
        this.authService = authService;
    }

    @GetMapping("/join")
    public String joinForm() {
    	//authService.checkMemberId("");
    	log.info("joinForm");
    	
    	return "/members/join";
    }
   
    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
    	// 에러가 있는지 검사
    	log.info("join");
    	System.out.println(member.getMemberAddress());
    	int isSuccess = memberService.insertMember(member);
    	System.out.println(isSuccess);
    	return "redirect:/members";
    }
    
    @GetMapping("/normalUpdate")
    public String normalUpdateForm() {
    	log.info("normalUpdateForm");
    	return "/member/normalUpdate";
    }
    
    @PostMapping("/normalUpdate")
    public String normalUpdate(@ModelAttribute Member member) {
    	// 에러가 있는지 검사
    	log.info("normalUpdate");
    	
    	int isSuccess = memberService.updateMember(member);
    	System.out.println(isSuccess);
    	return "redirect:/members";
    }
    
    @ResponseBody
    @GetMapping("getMember")
    public List<Member> findAll(){
    	return memberService.getMembers();
    }
    
    @GetMapping("/login")
    public String loginForm() {
    	log.info("loginForm");
    	return "/member/login";
    }
    
    @RequestMapping(value="/login", produces="application/json;charset=UTF-8", 
    method=RequestMethod.POST)
    public String login(@ModelAttribute Member member, HttpServletRequest request){
    	log.info("login");
    	Member mem = memberService.loginMember(member);
    	request.getSession().setAttribute("member",mem);
    	return "redirect:/members";
    }
}
