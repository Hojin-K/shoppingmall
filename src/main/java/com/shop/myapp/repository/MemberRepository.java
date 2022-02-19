package com.shop.myapp.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.shop.myapp.dto.Member;
import com.shop.myapp.dto.Seller;

@Mapper
public interface MemberRepository {
    int insertMember(Member member);
    int insertSellerInfo(Seller seller);
    int updateMember(Member member);
    List<Member> findAll();
    Optional<Member> findById();
    Optional<Member> checkUserAvailable(Member member);
}
