package com.shop.myapp.service;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.CartDetail;
import com.shop.myapp.repository.CartRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public CartService(@Autowired SqlSession sqlSession) {
        this.cartRepository = sqlSession.getMapper(CartRepository.class);
    }

    public int insertCart(Cart cart) {
        return cartRepository.insertCart(cart);
    }

    public List<CartDetail> findCartDetailByMemberId(String memberId){
        return cartRepository.findByMemberId(memberId);

    }
}
