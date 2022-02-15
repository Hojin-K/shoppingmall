package com.shop.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Item {
    private String itemCode;
    private String memberId;
    private String countryCode;
    private String itemName;
    private int itemPrice;
    private String itemImage;
    private String itemInfo;
    private List<ItemOption> options;
    private int itemStock;

    public void calculateItemStock() {
        for (ItemOption itemOption : options) {
            itemStock += itemOption.getOptionStock();
        }
    }
}
