package com.shop.myapp.repository;

import com.shop.myapp.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberRepository {
    int insertMember(Member member);
    int updateMember(Member member);
    List<Member> findAll();
    Optional<Member> findById(String memberId);
}
