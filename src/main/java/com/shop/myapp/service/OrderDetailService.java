package com.shop.myapp.service;

import com.shop.myapp.dto.OrderDetail;
import com.shop.myapp.repository.OrderDetailRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    public OrderDetailService(@Autowired SqlSession sqlSession) {
        this.orderDetailRepository = sqlSession.getMapper(OrderDetailRepository.class);
    }

    public int insertOrderDetails(List<OrderDetail> orderDetails){
        return orderDetailRepository.insertOrderDetails(orderDetails);

    }

    public int deleteOrderDetail(String orderCode){
        return orderDetailRepository.deleteOrderDetail(orderCode);
    }

    public int updatePostedStatusByOrderCode(String orderCode){
        return orderDetailRepository.updatePostedStatusByOrderCode(orderCode);
    }
}
