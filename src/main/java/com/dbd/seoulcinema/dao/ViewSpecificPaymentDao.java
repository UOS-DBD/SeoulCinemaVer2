package com.dbd.seoulcinema.dao;

import java.time.format.DateTimeFormatter;
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

    private String screeningStartTime;

    private String screeningEndTime;

    private LocalDate screeningDate;

    private String theaterFloor;

    private Integer standardPrice;

    private Integer totalPrice;

    private Integer paymentPoint;

    private String bankName;

    public ViewSpecificPaymentDao(String movieImg, String movieName, String ticketNumber,
        LocalDateTime screeningStartTime, LocalDateTime screeningEndTime, LocalDate screeningDate,
        String theaterFloor, Integer standardPrice, Integer totalPrice, Integer paymentPoint,
        String bankName) {
        this.movieImg = movieImg;
        this.movieName = movieName;
        this.ticketNumber = ticketNumber;
        this.screeningStartTime = screeningStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.screeningEndTime = screeningEndTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.screeningDate = screeningDate;
        this.theaterFloor = theaterFloor;
        this.standardPrice = standardPrice;
        this.totalPrice = totalPrice;
        this.paymentPoint = paymentPoint;
        this.bankName = bankName;
    }
}
