package com.dbd.seoulcinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScreeningTimeDto {
    private LocalDate screeningDate;
    private LocalDateTime screeningStartTime;
    private LocalDateTime screeningEndTime;
    private String scheduleNumber;
    private Integer screeningSession;
}
