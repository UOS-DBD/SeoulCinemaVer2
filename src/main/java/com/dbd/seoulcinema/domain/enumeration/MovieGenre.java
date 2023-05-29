package com.dbd.seoulcinema.domain.enumeration;

import lombok.Getter;

@Getter
public enum MovieGenre implements CommonDescCode {

    ACTION("액션", "01"),
    MELO("멜로", "02"),
    DRAMA("드라마", "03"),
    COMEDY("코미디", "04"),
    MARTIAL_ARTS("무협", "05"),
    SF("SF", "06"),
    EROTIC("에로", "07");


    private String desc;
    private String code;

    MovieGenre(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }
}
