package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;

@Data
@NoArgsConstructor
@Getter
public class CreateMovieDto {
    @JsonProperty("movieName")
    private String movieName;
    @JsonProperty("runningTime")
    private String runningTime;
    @JsonProperty("movieGenre")
    private MovieGenre movieGenre; // enum
    @JsonProperty("movieGrade")
    private MovieGrade movieGrade; // enum
    @JsonProperty("movieIntroduction")
    private String movieIntroduction;
    @JsonProperty("movieImage")
    private String movieImage;
    @JsonProperty("screeningStatus")
    private ScreeningStatus screeningStatus; // enum

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    private CreateMovieDto(
            @JsonProperty("movieName") String movieName,
            @JsonProperty("runningTime") String runningTime,
            @JsonProperty("movieGenre") MovieGenre movieGenre,
            @JsonProperty("movieGrade") MovieGrade movieGrade,
            @JsonProperty("movieIntroduction") String movieIntroduction,
            @JsonProperty("movieImage") String movieImage,
            @JsonProperty("screeningStatus") ScreeningStatus screeningStatus) {
        this.movieName = movieName;
        this.runningTime = runningTime;
        this.movieGenre = movieGenre;
        this.movieGrade = movieGrade;
        this.movieIntroduction = movieIntroduction;
        this.movieImage = movieImage;
        this.screeningStatus = screeningStatus;
    }
}
