package com.shop.myapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter @Setter
public class Order {

    private String orderCode;

    private String memberId;

    private long totalPay;

    private String buyerName;

    private String buyerEmail;

    private String buyerAddr;

    private String buyerPostCode;

    private String isPaid;

    private List<OrderDetail> orderDetails;

    public void setOrderCodeByDate(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.orderCode = now.format(formatter);

    }
    @Builder
    public Order(String orderCode, String memberId, long totalPay, String buyerName, String buyerEmail, String buyerAddr, String buyerPostCode, String isPaid, List<OrderDetail> orderDetails) {
        this.orderCode = orderCode;
        this.memberId = memberId;
        this.totalPay = totalPay;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerAddr = buyerAddr;
        this.buyerPostCode = buyerPostCode;
        this.isPaid = isPaid;
        this.orderDetails = orderDetails;
    }

    public Order() {
    }
}
