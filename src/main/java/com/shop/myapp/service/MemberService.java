package com.shop.myapp.service;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.shop.myapp.dto.Member;
import com.shop.myapp.dto.Seller;
import com.shop.myapp.repository.MemberRepository;

@Service
public class MemberService {
	private final SqlSession sqlSession;

	public MemberService(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Member getMember(String memberId){
		MemberRepository memberRepository = sqlSession.getMapper(MemberRepository.class);
		return memberRepository.
				findById(memberId).
				orElseThrow(()->new IllegalStateException(memberId +" 라는 id의 member 없음"));

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
    
public int insertSeller(Seller seller) {
    	
    	MemberRepository memberRepository 
    	= sqlSession.getMapper(MemberRepository.class);
		try {
			System.out.println("암호화 전 -->"+seller.getMemberPwd());
			seller.setMemberPwd(BCrypt.hashpw(seller.getMemberPwd(), BCrypt.gensalt()));			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("암호화 후 -->"+seller.getMemberPwd());
    	memberRepository.insertMember(seller);
		int result = memberRepository.insertSellerInfo(seller);
    	
    	return result;
    }
    
    public Member loginMember(Member member) {
    	MemberRepository memberRepository
    	= sqlSession.getMapper(MemberRepository.class);
    	String userPwd = member.getMemberPwd();
    	//로그인한 유저 id를 조회한다.
    	Optional<Member> loginMemberOptional = memberRepository.findById(member.getMemberId());
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

	public int updateMember(Member member) {
		MemberRepository memberRepository 
    	= sqlSession.getMapper(MemberRepository.class);
		try {
			System.out.println("암호화 전 -->"+member.getMemberPwd());
			member.setMemberPwd(BCrypt.hashpw(member.getMemberPwd(), BCrypt.gensalt()));			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("암호화 후 -->"+member.getMemberPwd());
		
    	int result = memberRepository.updateMember(member);
		return result;
	}
}
