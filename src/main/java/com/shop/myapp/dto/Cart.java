package com.shop.myapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Cart {
    // 주문 & 결제에 사용되는 Item 객체
    private String cartId;
    private String memberId;
    private String itemCode;
    private String itemOptionCode;
    private int itemAmount;


}
