package com.shop.myapp.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.shop.myapp.dto.Member;

@Mapper
public interface MemberRepository {
    int insertMember(Member member);
    int updateMember(Member member);
    List<Member> findAll();
    Optional<Member> findById(String memberId);
}
