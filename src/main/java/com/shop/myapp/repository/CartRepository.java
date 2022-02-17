package com.shop.myapp.repository;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.CartDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartRepository {
    int insertCart(Cart cartItem);
    List<CartDetail> findByMemberId(@Param("memberId") String memberId);
    int deleteCartByCartId(@Param("cartId") String cartId);
    int deleteCartByMemberId(@Param("memberId") String memberId);
}
