package com.dbd.seoulcinema.domain.enumeration;

public enum TicketingStatus {


    YES("예매완료"),
    NO("예매취소");

    String desc;
    TicketingStatus(String desc) {
        this.desc = desc;
    }
}
