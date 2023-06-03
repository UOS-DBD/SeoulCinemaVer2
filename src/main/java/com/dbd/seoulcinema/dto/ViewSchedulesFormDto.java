package com.dbd.seoulcinema.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ViewSchedulesFormDto {
    private Long movieNumber;
    private String movieName;
    private String scheduleNumber;
    private String screeningStartTime;
    private String screeningEndTime;
    private LocalDate screeningDate;
    private String theaterNumber;
    private String theaterFloor;
    private Long seatQuantity;
    private Long remainingSeat;
}
