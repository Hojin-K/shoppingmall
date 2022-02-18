package com.shop.myapp.repository;

import com.shop.myapp.dto.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailRepository {
    int insertOrderDetails(List<OrderDetail> orderDetails);
    OrderDetail findOrderDetailByOrderId();
}
