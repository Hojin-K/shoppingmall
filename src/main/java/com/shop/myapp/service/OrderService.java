package com.shop.myapp.service;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.Order;
import com.shop.myapp.dto.OrderDetail;
import com.shop.myapp.dto.Payment;
import com.shop.myapp.repository.OrderRepository;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;
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
    private final IamPortService iamPortService;


    public OrderService(@Autowired SqlSession sqlSession, OrderDetailService orderDetailService, CartService cartService, IamPortService iamPortService) {
        this.orderRepository = sqlSession.getMapper(OrderRepository.class);
        this.orderDetailService = orderDetailService;
        this.cartService = cartService;
        this.iamPortService = iamPortService;
    }

    public Order insertOrder(Order order, List<String> cartIds) {
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
        order.setOrderDetails(orderDetails);
        orderRepository.insertOrder(order);
        cartService.deleteByMemberId(order.getMemberId());
        orderDetailService.insertOrderDetails(orderDetails);
        return order;
    }

    public List<Cart> getSelectCartByCartIds(List<String> cartIds) {
        return cartService.findSelectCartByCartIds(cartIds);
    }

    public Payment validateTotalPay(String impUid, String orderCode) throws ParseException {
        try {
            Order order = orderRepository.findByOrderCode(orderCode);
            System.out.println(order.toString());
            String accessToken = iamPortService.getAccessToken();

            Payment payment = iamPortService.getImpAttributes(impUid, accessToken);
            System.out.println(payment.toString());
            if (order.getTotalPay() == payment.getAmount()) {
                int i = orderRepository.updateIsPaidIntByOrderCode(orderCode);
                System.out.println(i);
                i = orderDetailService.updatePostedStatusByOrderCode(orderCode);
                System.out.println(i);
                return payment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int cancelOrder(String orderCode) {
        orderDetailService.deleteOrderDetail(orderCode);
        return orderRepository.deleteOrderByOrderCode(orderCode);

    }

    public List<Order> myOrder(String memberId) {
        return orderRepository.findOrderByMemberId(memberId);
    }

    public Order findByOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);
    }
}
