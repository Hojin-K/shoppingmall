package com.shop.myapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetail {
    // item 객체와 itemOption 객체를 가지고 있는
    // Cart 의 디테일 객체 -> 장바구니 조회 & 주문으로 전달
    private String cartId; // 없어도 되는 필드로 보임.
    // memberId 로 해당 아이디의 Cart 조회
    private String memberId;
    // Cart 에 있는 Item
    private Item item;
    // Cart 에 있는 아이템의 옵션(사이즈)
    private ItemOption itemOption;
    // Cart 에 있는 동일한 Item 구매 갯수
    private int itemAmount;
}
