package com.shop.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        System.out.println("member.getMemberPwd() = " + member.getMemberPwd());
    	// 에러가 있는지 검사
    	log.info("join");
    	System.out.println(member.getMemberAddress());
    	int isSuccess = memberService.insertMember(member);
    	System.out.println(isSuccess);
    	return "redirect:/members";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
    	request.getSession().invalidate();
    	
    	return "redirect:/item";
    }
    
    @GetMapping("/update")
    public String updateForm() {
    	log.info("memberUpdateForm");
    	return "/members/update";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute Member member) {
    	// 에러가 있는지 검사
    	log.info("Edit member information.");
    	
    	int isSuccess = memberService.updateMember(member);
    	System.out.println(isSuccess);
    	log.info("update complete.");
    	return "redirect:/members";
    }
    
    @GetMapping("/login")
    public String loginForm() {
    	log.info("loginForm");
    	return "/members/login";
    }
    
    @RequestMapping(value="/login", produces="application/json;charset=UTF-8", 
    method=RequestMethod.POST)
    public String login(@ModelAttribute Member member, HttpServletRequest request){
        System.out.println("PWD : "+member.getMemberPwd());
    	log.info("login");
        try{
    	Member mem = memberService.loginMember(member);
    	request.getSession().setAttribute("member",mem);
        }catch (Exception e){
            e.printStackTrace();
        }

    	return "redirect:/item/home";
    }

    @GetMapping("/{memberId}")
    @ResponseBody
    public ResponseEntity<Object> getMemberInfo(@PathVariable String memberId){
        Member member = memberService.getMember(memberId);
        return ResponseEntity.ok(member);
    }
}
