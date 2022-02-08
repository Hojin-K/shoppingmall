package com.shop.myapp.controller;

import com.shop.myapp.dto.Member;
import com.shop.myapp.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public ResponseEntity<Object> helloController(){
        log.info("{}", this);
        return ResponseEntity.ok(memberService.getMember());
    }
    
    @RequestMapping("/normal/join")
    public String join(@ModelAttribute @Valid Member member, BindingResult result) {
    	// 에러가 있는지 검사
    	if( result.hasErrors() ) {

    		// 에러를 List로 저장
    		List<ObjectError> list = result.getAllErrors();
    		for( ObjectError error : list ) {
    			System.out.println(error);
    		}
    		return "/user/join";
    	}
    	
    	int isSuccess = memberService.insertMember(member);
    	System.out.println(isSuccess);
    	return "redirect:/user/joinsuccess";
    }
}
