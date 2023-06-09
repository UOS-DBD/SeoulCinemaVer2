package com.dbd.seoulcinema.domain.enumeration;

import lombok.Getter;

@Getter
public enum MovieGrade implements CommonDescCode {

    ALL("전체", "01"),
    TWELVE("12세", "02"),
    FIFTEEN("15세", "03"),
    ADULT("18세", "04");

    private String desc;
    private String code;

    MovieGrade(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }
}
