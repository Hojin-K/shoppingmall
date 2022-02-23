package com.shop.myapp.service;

import com.shop.myapp.dto.OrderDetail;
import com.shop.myapp.repository.OrderDetailRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(@Autowired SqlSession sqlSession) {
        this.orderDetailRepository = sqlSession.getMapper(OrderDetailRepository.class);
    }

    public void insertOrderDetails(List<OrderDetail> orderDetails){
        orderDetailRepository.insertOrderDetails(orderDetails);

    }

    public void deleteOrderDetail(String orderCode){
        orderDetailRepository.deleteOrderDetail(orderCode);
    }

    public void updatePostedStatusByOrderCode(String orderCode){
        orderDetailRepository.updatePostedStatusByOrderCode(orderCode);
    }
}
