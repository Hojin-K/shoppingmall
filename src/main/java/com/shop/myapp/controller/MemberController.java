package com.shop.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.myapp.dto.Member;
import com.shop.myapp.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
  

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String joinForm() {
    	log.info("joinForm");
    	return "/member/join";
    }
   
    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
    	// 에러가 있는지 검사
    	log.info("join");
    	System.out.println(member.getMemberId());
    	int isSuccess = memberService.insertMember(member);
    	System.out.println(isSuccess);
    	return "redirect:/members";
    }
    
    @ResponseBody
    @GetMapping("getMember")
    public List<Member> findAll(){
    	return memberService.getMember();
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
    	return "보낼 주소";
    }
}
