package com.shop.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class OrderDetail {

    private String orderCode;
    private Item item;
    private ItemOption itemOption;
    private int amount;
    private String postedStatus;


}
