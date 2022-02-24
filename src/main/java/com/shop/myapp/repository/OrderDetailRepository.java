package com.shop.myapp.repository;

import com.shop.myapp.dto.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailRepository {
    int insertOrderDetails(List<OrderDetail> orderDetails);
    OrderDetail findByOrderDetailCode(String orderDetailCode);
    int deleteOrderDetail(String orderCode);
    int updatePostedStatusByOrderCode(String orderCode);
    int updateWhenCancel(String orderDetailCode);
}
