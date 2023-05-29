package com.dbd.seoulcinema.domain.enumeration;

import lombok.Getter;

@Getter
public enum ParticipantType implements CommonDescCode {

    DIRECTOR("감독", "01"),
    ACTOR("배우", "02"),
    DISTRIBUTOR("배급사", "03");

    private String desc;
    private String code;

    ParticipantType(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }
}
