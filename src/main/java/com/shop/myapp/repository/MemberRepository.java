package com.shop.myapp.repository;

import com.shop.myapp.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {
    int insertMember(Member member);
    List<Member> findAll();
}
