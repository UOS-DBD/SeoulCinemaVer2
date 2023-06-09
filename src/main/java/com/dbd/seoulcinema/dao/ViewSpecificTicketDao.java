package com.dbd.seoulcinema.dao;

import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ViewSpecificTicketDao {

    private String movieImg;

    private String movieName;

    private String screeningStartTime;

    private String screeningEndTime;

    private LocalDate screeningDate;

    private String theaterFloor;

    public ViewSpecificTicketDao(String movieImg, String movieName,
        LocalDateTime screeningStartTime,
        LocalDateTime screeningEndTime, LocalDate screeningDate, String theaterFloor) {
        this.movieImg = movieImg;
        this.movieName = movieName;
        this.screeningStartTime = screeningStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.screeningEndTime = screeningEndTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.screeningDate = screeningDate;
        this.theaterFloor = theaterFloor;
    }
}
