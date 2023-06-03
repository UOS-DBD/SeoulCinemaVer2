package com.dbd.seoulcinema.domain.enumeration;

import lombok.Getter;

@Getter
public enum DiscountType implements CommonDescCode {

    KB("국민", 1000, "01"),
    SAMSUNG("삼성", 2000, "02"),
    TOSS("토스", 3000, "03"),
    SHINHAN("신한", 3500,"04"),
    SKT("SKT", 1000,"04"),
    KT("KT", 1000,"06"),
    POINTDISCOUNT("포인트차감", 0,"07"),
    BC("BC",0,"08"),
    WOORI("WOORI",0,"09");

    private String desc;
    private Integer amount;
    private String code;

    DiscountType(String desc, Integer amount, String code) {
        this.desc = desc;
        this.amount = amount;
        this.code = code;
    }
}
