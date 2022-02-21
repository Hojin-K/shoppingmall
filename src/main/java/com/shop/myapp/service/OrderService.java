package com.shop.myapp.service;

import com.shop.myapp.dto.*;
import com.shop.myapp.repository.OrderRepository;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ItemOptionService itemOptionService;
    private final CartService cartService;
    private final IamPortService iamPortService;


    public OrderService(@Autowired SqlSession sqlSession, OrderDetailService orderDetailService, ItemOptionService itemOptionService, CartService cartService, IamPortService iamPortService) {
        this.orderRepository = sqlSession.getMapper(OrderRepository.class);
        this.orderDetailService = orderDetailService;
        this.itemOptionService = itemOptionService;
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
                orderRepository.updateIsPaidIntByOrderCode(orderCode);
                orderDetailService.updatePostedStatusByOrderCode(orderCode);
                cartService.deleteByMemberId(order.getMemberId());
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


    public Order findByOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);
    }
    public List<Order> myOrder(String memberId){
        List<Order> orders = orderRepository.findOrderByMemberId(memberId);
        for (Order order : orders){
            List<OrderDetail> orderDetails = order.getOrderDetails();
            for(OrderDetail orderDetail : orderDetails){
                Optional<ItemOption> itemOptionOptional = itemOptionService.findByOptionCode(orderDetail.getOptionCode());
                ItemOption itemOption = itemOptionOptional.orElseThrow(() -> new IllegalStateException("없는 아이템"));
                orderDetail.setItemOption(itemOption);
            }
        }
        return orders;
    }
}
