package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieDto {
    private String movieName;
    private String runningTime;
    private String movieGenre; // enum
    private String movieGrade; // enum
    private String movieIntroduction;
    private String movieImage;
    private String screeningStatus; // enum
}
