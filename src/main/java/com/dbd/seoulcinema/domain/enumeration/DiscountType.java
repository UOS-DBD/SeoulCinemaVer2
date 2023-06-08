package com.dbd.seoulcinema.domain.enumeration;

import lombok.Getter;

@Getter
public enum DiscountType implements CommonDescCode {

    KB("국민카드 결제", 1000, "01"),
    SAMSUNG("삼성카드 결제", 2000, "02"),
    TOSS("토스 결제", 3000, "03"),
    SHINHAN("신한카드 결제", 3500,"04"),
    SKT("SKT", 1000,"05"),
    KT("KT", 1000,"06"),
    POINTDISCOUNT("포인트차감", 0,"07"),
    BC("BC카드 결제",0,"08"),
    WOORI("우리카드 결제",0,"09"),
    ETC("기타",0,"10");

    private String desc;
    private Integer amount;
    private String code;

    DiscountType(String desc, Integer amount, String code) {
        this.desc = desc;
        this.amount = amount;
        this.code = code;
    }
}
