package com.shop.myapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Payment {
    private String impUid;
    private String name;
    private long amount;
    private String buyerName;
    private String buyerEmail;
    private String buyerTel;
    private String buyerAddr;
    private String buyerPostCode;
    private String status;

    @Builder
    public Payment(String impUid, String name, long amount, String buyerName, String buyerEmail, String buyerTel, String buyerAddr, String buyerPostCode, String status) {
        this.impUid = impUid;
        this.name = name;
        this.amount = amount;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerTel = buyerTel;
        this.buyerAddr = buyerAddr;
        this.buyerPostCode = buyerPostCode;
        this.status = status;
    }
}
