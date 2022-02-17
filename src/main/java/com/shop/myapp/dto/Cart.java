package com.shop.myapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Cart {
    // view 에서 받을때, 사용하는 객체
    private String cartId;
    private String memberId;
    private String itemOptionCode;
    private int itemAmount;


}
