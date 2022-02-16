package com.shop.myapp.repository;

import com.shop.myapp.dto.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRepository {
    int insertOrder(Order order);

    int paidOrder(@Param("orderCode") String orderCode);

    int deleteOrder(@Param("orderCode") String orderCode);




}
