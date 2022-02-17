package com.shop.myapp.dto;

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

    private String status;

    private List<OrderDetail> orderDetails;

    public void setOrderCode(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.orderCode = now.format(formatter);

    }
}
