package com.dbd.seoulcinema.domain.enumeration;

import lombok.Getter;

@Getter
public enum PaymentType implements CommonDescCode {

    CARD("카드", "01"),
    ACCOUNT("계좌", "02"),
    POINT("포인트", "03");

    private String desc;
    private String code;

    PaymentType(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

}
