package com.shop.myapp.service;

import com.shop.myapp.dto.Member;
import com.shop.myapp.repository.MemberRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
	@Autowired
	private SqlSession sqlSession;
	
    public List<Member> getMember(){
        List<Member> members = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Member member = new Member();
            member.setMember_id("홍길동"+i);
            member.setMember_level(i);
            members.add(member);
        }
        return members;
    }
    
    public int insertMember(Member member) {
    	
    	MemberRepository memberRepository 
    	= sqlSession.getMapper(MemberRepository.class);
    	
    	int result = memberRepository.insertMember(member);
    	
    	return result;
    }
}
