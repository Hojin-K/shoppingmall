package com.shop.myapp.repository;

import com.shop.myapp.dto.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartRepository {
    int insertItem(Cart cartItem);
    List<Cart>
}
