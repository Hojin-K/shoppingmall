package com.shop.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.myapp.dto.Member;

@Service
public class MemberService {

    public List<Member> getMember(){
        List<Member> members = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Member member = new Member();
            member.setName("홍길동"+i);
            member.setAge(i);
            members.add(member);
        }
        return members;
    }
}
