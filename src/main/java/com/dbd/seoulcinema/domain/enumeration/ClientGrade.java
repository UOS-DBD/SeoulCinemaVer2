package com.dbd.seoulcinema.domain.enumeration;

import lombok.Getter;

@Getter
public enum ClientGrade implements CommonDescCode {

    NORMAL("일반", "01"),
    VIP("VIP", "02");

    private String desc;
    private String code;

    ClientGrade(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }
}
