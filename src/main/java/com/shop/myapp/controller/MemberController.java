package com.shop.myapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.shop.myapp.dto.Country;
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
    
    @GetMapping("/normalUpdate")
    public String normalUpdateForm() {
    	log.info("normalUpdateForm");
    	return "/members/normalUpdate";
    }
    
    @PostMapping("/normalUpdate")
    public String normalUpdate(@ModelAttribute Member member) {
    	// 에러가 있는지 검사
    	log.info("normalUpdate");
    	
    	int isSuccess = memberService.updateMember(member);
    	System.out.println(isSuccess);
    	return "redirect:/members";
    }
    
    @GetMapping("/memberListView")
    public String memberList() {
    	return "/members/memberList";
    }
    
    @ResponseBody
    @GetMapping("/memberList")
    public ResponseEntity<Object> getMemberList(@RequestParam Map<String, Object> param){
    	log.info("memberList");
        List<Member> members = memberService.getMembers(param);
        return ResponseEntity.ok(members);
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

    	return "redirect:/members";
    }

    @GetMapping("/{memberId}")
    @ResponseBody
    public ResponseEntity<Object> getMemberInfo(@PathVariable String memberId){
        Member member = memberService.getMember(memberId);
        return ResponseEntity.ok(member);
    }
}
