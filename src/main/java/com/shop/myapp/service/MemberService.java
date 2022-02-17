package com.shop.myapp.service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.shop.myapp.dto.Member;
import com.shop.myapp.repository.MemberRepository;
>>>>>>> bdacc78d5044f8cc8ffab06b6772ab75d3d63c26

import org.springframework.stereotype.Service;

import com.shop.myapp.dto.Member;

@Service
public class MemberService {
	private final SqlSession sqlSession;

	public MemberService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Member> getMembers(){
    	MemberRepository memberRepository = sqlSession.getMapper(MemberRepository.class);
    	List<Member> members = memberRepository.findAll();
    	members.forEach(member -> System.out.print(member.getMemberName()));
    	for(Member member : members) {
    		System.out.println(member.getMemberBirth());
    	}
        return members;
    }
    
    public int insertMember(Member member) {
    	
    	MemberRepository memberRepository 
    	= sqlSession.getMapper(MemberRepository.class);
		try {
			System.out.println("암호화 전 -->"+member.getMemberPwd());
			member.setMemberPwd(BCrypt.hashpw(member.getMemberPwd(), BCrypt.gensalt()));			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("암호화 후 -->"+member.getMemberPwd());
    	int result = memberRepository.insertMember(member);
    	
    	return result;
    }
    
    public String loginMember(Member member) {
    	MemberRepository memberRepository
    	= sqlSession.getMapper(MemberRepository.class);
    	String userPwd = member.getMemberPwd();
    	Member loginMember = memberRepository.checkUserAvailable(member);
    	 
    	String msg = "";
    	
    	if(loginMember.getMemberId() != null) {
    		if(BCrypt.checkpw(userPwd,  loginMember.getMemberPwd())){
    			if(isManager(loginMember.getMemberLevel())) {
    				msg = "관리자님 어서오세요.";
    			}else {
    				msg = loginMember.getMemberName()+"님 환영합니다.";
    			}
    		}else {
    			msg = "비밀번호가 틀렸습니다. 다시 입력해주세요.";
    		}
    	}else {
    		msg = "등록된 ID가 없습니다. 다시 입력해주세요.";
    	}
    	
    	return msg;
    }
    
    public boolean isManager(String memberLevel) {
    	return Integer.parseInt(memberLevel) == 5;
    }
}
