package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieDetailDto {
    private Long movieNumber;//
    private String movieName;//
    private String runningTime;//
    private MovieGenre movieGenre;//
    private MovieGrade movieGrade;//
    private String movieIntroduction;//
    private String movieImage;//
    private ScreeningStatus screeningStatus;//
    private Long participantNumber;
    private String participantName;
    private ParticipantType participantType;

}
