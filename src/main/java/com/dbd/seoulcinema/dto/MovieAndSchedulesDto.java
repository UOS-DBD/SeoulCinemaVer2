package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import lombok.Data;
import lombok.Getter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MovieAndSchedulesDto {
    private Long movieNumber;
    private String movieName;
    private MovieGrade movieGrade;
    private String scheduleNumber;
    private LocalDateTime screeningStartTime;
    private LocalDateTime screeningEndTime;
    private LocalDate screeningDate;

    public MovieAndSchedulesDto(Long movieNumber, String movieName, MovieGrade movieGrade, String scheduleNumber, LocalDateTime screeningStartTime, LocalDateTime screeningEndTime, LocalDate screeningDate) {
        this.movieNumber = movieNumber;
        this.movieName = movieName;
        this.movieGrade = movieGrade;
        this.scheduleNumber = scheduleNumber;
        this.screeningStartTime = screeningStartTime;
        this.screeningEndTime = screeningEndTime;
        this.screeningDate = screeningDate;
    }
}
