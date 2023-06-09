package com.dbd.seoulcinema.dto;

import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ViewTicketsListDto {

    private String ticketNumber;

    private String movieName;

    private String screeningStartTime;

    private String screeningEndTime;

    public ViewTicketsListDto(String ticketNumber, String movieName, LocalDateTime screeningStartTime,
        LocalDateTime screeningEndTime) {
        this.ticketNumber = ticketNumber;
        this.movieName = movieName;
        this.screeningStartTime = screeningStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.screeningEndTime = screeningEndTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
