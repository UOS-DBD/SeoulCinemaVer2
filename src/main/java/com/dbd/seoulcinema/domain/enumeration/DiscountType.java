package com.dbd.seoulcinema.domain.enumeration;

import lombok.Getter;

@Getter
public enum DiscountType implements CommonDescCode {

    KB("국민", "01"),
    SAMSUNG("삼성", "02"),
    TOSS("토스", "03"),
    SHINHAN("신한", "04"),
    SKT("SKT", "04"),
    KT("KT", "06"),
    POINTDISCOUNT("포인트차감", "07");

    private String desc;
    private String code;

    DiscountType(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }
}
