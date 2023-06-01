package com.dbd.seoulcinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewTicketsListDto {

    private String movieImage;

    private String movieName;

    private LocalDateTime screeningStartTime;

    private LocalDateTime screeningEndTime;

    private LocalDateTime screeningDate;
}
