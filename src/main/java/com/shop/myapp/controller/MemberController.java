package com.shop.myapp.controller;

import com.shop.myapp.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {

	@Autowired
    private MemberService memberService;

    /*public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }*/

    @GetMapping("")
    public ResponseEntity<Object> helloController(){
        log.info("{}", this);
        return ResponseEntity.ok(memberService.getMember());
    }
}
