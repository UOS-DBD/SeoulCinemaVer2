package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.dao.ViewSpecificTicketDao;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ViewSpecificTicketDto {

    private String movieImg;

    private String movieName;
    private String screeningStartTime;

    private String screeningEndTime;

    private LocalDate screeningDate;

    private String theaterFloor;

    private String seats;

    public ViewSpecificTicketDto(ViewSpecificTicketDao dao, String seatInfo) {
        this.movieImg = dao.getMovieImg();
        this.movieName = dao.getMovieName();
        this.screeningStartTime = dao.getScreeningStartTime();
        this.screeningEndTime = dao.getScreeningEndTime();
        this.screeningDate = dao.getScreeningDate();
        this.theaterFloor = dao.getTheaterFloor();
        this.seats = seatInfo;
    }
}
