package com.shop.myapp.service;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.shop.myapp.dto.Member;
import com.shop.myapp.repository.MemberRepository;

@Service
public class MemberService {
	private final SqlSession sqlSession;

	public MemberService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Member> getMember(){
    	MemberRepository memberRepository 
    	= sqlSession.getMapper(MemberRepository.class);
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
    
    public Member loginMember(Member member) {
    	MemberRepository memberRepository
    	= sqlSession.getMapper(MemberRepository.class);
    	String userPwd = member.getMemberPwd();
    	//로그인한 유저 id를 조회한다.
    	Optional<Member> loginMemberOptional = memberRepository.checkUserAvailable(member);
    	if(loginMemberOptional.isPresent()) {
    		Member loginMember = loginMemberOptional.get();
    		if(BCrypt.checkpw(userPwd,  loginMember.getMemberPwd())){
    			return loginMember;
    		}
    	}
    	return null;
    }
    
    public boolean isManager(String memberLevel) {
    	return Integer.parseInt(memberLevel) == 5;
    }
}
