package com.shop.myapp.service;

import com.shop.myapp.dto.*;
import com.shop.myapp.repository.OrderRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ItemOptionService itemOptionService;
    private final CartService cartService;
    private final IamPortService iamPortService;


    public OrderService(@Autowired SqlSession sqlSession, @Lazy OrderDetailService orderDetailService, ItemOptionService itemOptionService, CartService cartService, IamPortService iamPortService) {
        this.orderRepository = sqlSession.getMapper(OrderRepository.class);
        this.orderDetailService = orderDetailService;
        this.itemOptionService = itemOptionService;
        this.cartService = cartService;
        this.iamPortService = iamPortService;
    }

    public Order insertOrder(Order order, List<String> cartIds) throws Exception {
        order.setOrderCodeByDate();
        List<Cart> carts = cartService.findSelectCartByCartIds(cartIds);
        List<OrderDetail> orderDetails = new ArrayList<>();
        int total = 0;
        for (Cart cart : carts) {
            // 제고 확인
            boolean isValidated = optionStockValidate(cart);
            System.out.println(isValidated);
            if (isValidated){
            // cart 내부 매서드로 cart -> orderDetail 로 변환
            OrderDetail orderDetail = cart.parseToOrderDetail(order);
            orderDetails.add(orderDetail);
            int amount = cart.getAmount();
                int itemPrice = cart.getItemOption().getItem().getItemPrice();
                int countryPostPrice = cart.getItemOption().getItem().getCountry().getCountryPostPrice();
                int orderDetailPrice = itemPrice + countryPostPrice;
            total += amount * orderDetailPrice;
            }
        }
        order.setTotalPay(total);
        order.setOrderDetails(orderDetails);
        orderRepository.insertOrder(order);
        orderDetailService.insertOrderDetails(orderDetails);
        return order;
    }

    public boolean optionStockValidate(Cart cart){
        ItemOption itemOption = cart.getItemOption();
        return itemOption.getOptionStock() != 0 && !itemOption.getIsDelete().equals("1") && !itemOption.getItem().getIsDelete().equals("1");
    }

    public List<Cart> getSelectCartByCartIds(List<String> cartIds) {
        return cartService.findSelectCartByCartIds(cartIds);
    }

    public Payment validateTotalPay(String impUid, String orderCode) {
        try {
            Order order = orderRepository.findByOrderCode(orderCode);

            Payment payment = iamPortService.getImpAttributes(impUid);
            if (order.getTotalPay() == payment.getAmount()) {
                orderRepository.updateIsPaidIntByOrderCode(orderCode,payment);
                orderDetailService.updatePostedStatusByOrderCode(orderCode);
                itemOptionService.modifyItemOptionAfterPay(order.getOrderDetails());
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
                Optional<ItemOption> itemOptionOptional = itemOptionService.findByOptionCodeForOrder(orderDetail.getOptionCode());
                ItemOption itemOption = itemOptionOptional.orElseThrow(() -> new IllegalStateException("없는 아이템"));
                orderDetail.setItemOption(itemOption);
            }
        }
        return orders;
    }

    public int updateChangeWhenCancel(OrderDetail orderDetail){
        return orderRepository.updateChangeWhenCancel(orderDetail);
    }
}
