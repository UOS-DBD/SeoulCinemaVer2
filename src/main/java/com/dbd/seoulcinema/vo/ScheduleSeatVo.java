package com.dbd.seoulcinema.vo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ScheduleSeatVo {

    String scheduleNumber;

    List<Long> seats;

    public ScheduleSeatVo(String scheduleNumber, List<Long> seats) {
        this.scheduleNumber = scheduleNumber;
        this.seats = seats;
    }
}
