package com.dbd.seoulcinema.dto;


import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MovieAndSchedulesDto {
    private Long movieNumber;
    private String movieName;
    private String scheduleNumber;
    private String screeningStartTime;
    private String screeningEndTime;
    private LocalDate screeningDate;
    private String theaterNumber;
    private String theaterFloor;
    private Long seatQuantity;

    public MovieAndSchedulesDto(Long movieNumber, String movieName, String scheduleNumber, LocalDateTime screeningStartTime, LocalDateTime screeningEndTime, LocalDate screeningDate, String theaterNumber, String theaterFloor, Long seatQuantity) {
        this.movieNumber = movieNumber;
        this.movieName = movieName;
        this.scheduleNumber = scheduleNumber;
        this.screeningStartTime = screeningStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.screeningEndTime = screeningEndTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.screeningDate = screeningDate;
        this.theaterNumber = theaterNumber;
        this.seatQuantity = seatQuantity;
        this.theaterFloor = theaterFloor;
    }
}
