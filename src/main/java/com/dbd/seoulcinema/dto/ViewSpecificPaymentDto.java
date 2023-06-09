package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.dao.ViewSpecificPaymentDao;
import com.dbd.seoulcinema.domain.entity.Discount;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ViewSpecificPaymentDto {

    private String movieImg;

    private String movieName;

    private String ticketNumber;

    private String screeningStartTime;

    private String screeningEndTime;

    private LocalDate screeningDate;

    private String theaterFloor;

    private Integer standardPrice;

    private Integer totalPrice;

    private String seatLocation;

    private List<Discount> discounts = new ArrayList<>();

    private Integer paymentPoint;

    private String bankName;

    public ViewSpecificPaymentDto(ViewSpecificPaymentDao dao, String seatLocation, List<Discount> discounts) {
        this.movieImg = dao.getMovieImg();
        this.movieName = dao.getMovieName();
        this.ticketNumber = dao.getTicketNumber();
        this.screeningStartTime = dao.getScreeningStartTime();
        this.screeningEndTime = dao.getScreeningEndTime();
        this.screeningDate = dao.getScreeningDate();
        this.theaterFloor = dao.getTheaterFloor();
        this.standardPrice = dao.getStandardPrice();
        this.totalPrice = dao.getTotalPrice();
        this.seatLocation = seatLocation;
        this.discounts = discounts;
        this.paymentPoint = dao.getPaymentPoint();
        this.bankName = dao.getBankName();
    }
}
