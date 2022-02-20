package com.shop.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.shop.myapp.dto.Member;
import com.shop.myapp.interceptor.Auth;
import com.shop.myapp.interceptor.Auth.Role;
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
    @GetMapping("")
    public List<Member> findAll() {
        return memberService.getMembers();
    }

    @ResponseBody
    @GetMapping("/{memberId}")
    public ResponseEntity<Object> findByMemberId(@PathVariable String memberId, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member.getMemberId().equals(memberId)) {
            return ResponseEntity.ok(memberService.getMember(memberId));
        } else {
            return ResponseEntity.status(402).build();
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        log.info("loginForm");
        return "/member/login";
    }

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    public String login(@ModelAttribute Member memberInfo, HttpServletRequest request) {
        log.info("login");
        Member member = memberService.loginMember(memberInfo);
        request.getSession().setAttribute("member", member);
        return "redirect:/members";
    }
}
