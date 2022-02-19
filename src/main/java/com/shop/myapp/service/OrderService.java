package com.shop.myapp.service;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.Order;
import com.shop.myapp.dto.OrderDetail;
import com.shop.myapp.repository.OrderRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final CartService cartService;


    public OrderService(@Autowired SqlSession sqlSession, OrderDetailService orderDetailService, CartService cartService) {
        this.orderRepository = sqlSession.getMapper(OrderRepository.class);
        this.orderDetailService = orderDetailService;
        this.cartService = cartService;
    }

    public int insertOrder(Order order, List<String> cartIds) {
        order.setOrderCodeByDate();
        List<Cart> carts = cartService.findSelectCartByCartIds(cartIds);
        List<OrderDetail> orderDetails = new ArrayList<>();
        int total = 0;
        for (Cart cart : carts) {
            OrderDetail orderDetail = cart.parseToOrderDetail(order);
            orderDetails.add(orderDetail);
            int amount = cart.getAmount();
            int itemPrice = cart.getItemOption().getItem().getItemPrice();
            total += amount * itemPrice;
        }
        order.setTotalPay(total);
        int result = orderRepository.insertOrder(order);
        return orderDetailService.insertOrderDetails(orderDetails);
    }

    public List<Cart> getSelectCartByCartIds(List<String> cartIds){
        return cartService.findSelectCartByCartIds(cartIds);
    }

}
