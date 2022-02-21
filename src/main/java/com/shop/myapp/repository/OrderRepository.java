package com.shop.myapp.repository;

import com.shop.myapp.dto.Order;
import com.shop.myapp.dto.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderRepository {
    int insertOrder(Order order);

    int deleteOrderByOrderCode(@Param("orderCode") String orderCode);

    List<Order> findOrderByMemberId(String memberId);
    Order findByOrderCode(String orderCode);
    int updateIsPaidIntByOrderCode(String orderCode);
}
