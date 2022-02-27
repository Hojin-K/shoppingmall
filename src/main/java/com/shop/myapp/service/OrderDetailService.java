package com.shop.myapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.myapp.dto.OrderDetail;
import com.shop.myapp.repository.OrderDetailRepository;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = {Exception.class})
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final IamPortService iamPortService;
    private final OrderService orderService;
    private final ItemOptionService itemOptionService;

    public OrderDetailService(@Autowired SqlSession sqlSession, IamPortService iamPortService, @Lazy OrderService orderService, ItemOptionService itemOptionService) {
        this.orderDetailRepository = sqlSession.getMapper(OrderDetailRepository.class);
        this.iamPortService = iamPortService;
        this.orderService = orderService;
        this.itemOptionService = itemOptionService;
    }

    public void insertOrderDetails(List<OrderDetail> orderDetails) {
        orderDetailRepository.insertOrderDetails(orderDetails);

    }

    public void deleteOrderDetail(String orderCode) {
        orderDetailRepository.deleteOrderDetail(orderCode);
    }

    public void updatePostedStatusByOrderCode(String orderCode) {
        orderDetailRepository.updatePostedStatusByOrderCode(orderCode);
    }

    public OrderDetail findByOrderDetailCode(String orderDetailCode) {
        return orderDetailRepository.findByOrderDetailCode(orderDetailCode);
    }

    public boolean orderDetailRefund(OrderDetail orderDetail) throws ParseException, JsonProcessingException {
        Map<String, Object> refundDetail = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(orderDetail.getOrder().getImpUid());
        refundDetail.put("imp_uid", orderDetail.getOrder().getImpUid());
        refundDetail.put("amount", ((double) orderDetail.getAmount() * orderDetail.getOrderPrice()) + (orderDetail.getAmount() * orderDetail.getPostPrice()));
        String refundDetailString = objectMapper.writeValueAsString(refundDetail);
        System.out.println(refundDetailString);
        long refund = ((long) orderDetail.getAmount() * orderDetail.getOrderPrice()) + ((long) orderDetail.getAmount() * orderDetail.getPostPrice());
        System.out.println("refund =  "+refund);
        if (iamPortService.cancel(refundDetailString) == (orderDetail.getOrder().getChange()+ refund)) {
            orderDetailRepository.updateWhenCancel(orderDetail.getOrderDetailCode());
            return true;
        }
        return false;
    }
    public boolean orderCancelService(OrderDetail orderDetail) throws ParseException, JsonProcessingException {
        if (orderDetailRefund(orderDetail)){
            updateWhenCancel(orderDetail.getOrderDetailCode());
            orderService.updateChangeWhenCancel(orderDetail);
            itemOptionService.modifyItemOptionAfterRefund(orderDetail);
            return true;
        }
        return false;
    }

    public int updateWhenCancel(String orderDetailCode) {
        return orderDetailRepository.updateWhenCancel(orderDetailCode);
    }

    public List<OrderDetail> getOrderDetailByItemWriter(String memberId){
        return orderDetailRepository.findByMemberIdForSeller(memberId);
    }

    public int updatePostedStatusByOrderDetailCode(String orderDetailCode, String postedStatus){
        return orderDetailRepository.updatePostedStatusByOrderDetailCode(orderDetailCode, postedStatus);
    }

    public int updatePostedStatusByOrderDetailCodeAfterReview(String orderDetailCode){
        return orderDetailRepository.updatePostedStatusByOrderDetailCodeAfterReview(orderDetailCode);
    }
}
