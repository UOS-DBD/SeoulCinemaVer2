package com.dbd.seoulcinema.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewSpecificPaymentDao {

    private String movieImg;

    private String movieName;

    private String ticketNumber;

    private LocalDateTime screeningStartTime;

    private LocalDateTime screeningEndTime;

    private LocalDate screeningDate;

    private String theaterFloor;

    private Integer standardPrice;

    private Integer totalPrice;

    private Integer paymentPoint;

    private String bankName;
}
