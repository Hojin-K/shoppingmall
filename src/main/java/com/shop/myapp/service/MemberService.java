package com.shop.myapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shop.myapp.dto.Item;
import com.shop.myapp.dto.Pagination;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.shop.myapp.dto.Member;
import com.shop.myapp.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
    private final SqlSession sqlSession;
    private final ItemService itemService;

    public MemberService(SqlSession sqlSession, ItemService itemService) {
        this.sqlSession = sqlSession;
        this.itemService = itemService;
    }

    public Member getMember(String memberId) {
        MemberRepository memberRepository = sqlSession.getMapper(MemberRepository.class);
        return memberRepository.
                findById(memberId).
                orElseThrow(() -> new IllegalStateException(memberId + " 라는 id의 member 없음"));
    }

    public List<Member> getMembers(String chkInfo, String condition) {
        MemberRepository memberRepository = sqlSession.getMapper(MemberRepository.class);
        List<Member> members = new ArrayList<Member>(); 
        try{
        	System.out.println(1);
        	members = memberRepository.findAll(chkInfo, condition);
        	System.out.println(2);
        }catch (Exception e) {
			e.printStackTrace();
		}
        //members.forEach(member -> System.out.print(member.getMemberName()));
        for (Member member : members) {
            System.out.println(member.getMemberBirth());
        }
        return members;
    }

    public int insertMember(Member member) {
    	log.info(this.getClass()+"--->>> [MEMBER INSERT]");
    	System.out.println("1--> " +member.getMemberLevel());
    	System.out.println("2--> " +member.getMemberLevelToString());
    	MemberRepository memberRepository
                = sqlSession.getMapper(MemberRepository.class);
        try {
            System.out.println("암호화 전 -->" + member.getMemberPwd());
            member.setMemberPwd(BCrypt.hashpw(member.getMemberPwd(), BCrypt.gensalt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("암호화 후 -->" + member.getMemberPwd());
        int result = memberRepository.insertMember(member);

        return result;
    }

    public Member loginMember(Member member) {
        MemberRepository memberRepository
                = sqlSession.getMapper(MemberRepository.class);
        String userPwd = member.getMemberPwd();
        //로그인한 유저 id를 조회한다.
        Optional<Member> loginMemberOptional = memberRepository.findById(member.getMemberId());
        Member loginMember = loginMemberOptional.
        		orElseThrow(() -> new IllegalStateException("아이디가 틀리거나 없는 회원입니다.") );
        if (BCrypt.checkpw(userPwd, loginMember.getMemberPwd())) {
            System.out.println("true");
            return loginMember;
        } else {
        	throw new IllegalStateException("비밀번호 오류입니다.");
        }
    }

    public boolean isManager(String memberLevel) {
        return Integer.parseInt(memberLevel) == 5;
    }

    public int updateMember(Member member) {
        MemberRepository memberRepository
                = sqlSession.getMapper(MemberRepository.class);
        try {
            System.out.println("암호화 전 -->" + member.getMemberPwd());
            member.setMemberPwd(BCrypt.hashpw(member.getMemberPwd(), BCrypt.gensalt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("암호화 후 -->" + member.getMemberPwd());

        return memberRepository.updateMember(member);
    }

    public List<Item> getSellerItems(String memberId, Pagination pagination,String search){
        return itemService.getSellerItemByMemberId(memberId, pagination,search);
    }

    public int updateSellerInfo(Member member){
        MemberRepository memberRepository
                = sqlSession.getMapper(MemberRepository.class);
        return memberRepository.updateSeller(member);
    }
    
    public int updateByAdmin(Member member) {
    	 MemberRepository memberRepository
         		= sqlSession.getMapper(MemberRepository.class);
    	 try {
             System.out.println("암호화 전 -->" + member.getMemberPwd());
             member.setMemberPwd(BCrypt.hashpw(member.getMemberPwd(), BCrypt.gensalt()));
         } catch (Exception e) {
             e.printStackTrace();
         }
         System.out.println("암호화 후 -->" + member.getMemberPwd());
         
    	return memberRepository.updateByAdmin(member);
    }
}
