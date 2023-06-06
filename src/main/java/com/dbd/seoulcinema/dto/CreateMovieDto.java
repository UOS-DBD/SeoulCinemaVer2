package com.dbd.seoulcinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;

@Data
@NoArgsConstructor
public class CreateMovieDto {
    @JsonProperty("movieName")
    private String movieName;
    @JsonProperty("runningTime")
    private String runningTime;
    @JsonProperty("movieGenre")
    private String movieGenre; // enum
    @JsonProperty("movieGrade")
    private String movieGrade; // enum
    @JsonProperty("movieIntroduction")
    private String movieIntroduction;
    @JsonProperty("movieImage")
    private String movieImage;
    @JsonProperty("screeningStatus")
    private String screeningStatus; // enum

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    private CreateMovieDto(
            @JsonProperty("movieName") String movieName,
            @JsonProperty("runningTime") String runningTime,
            @JsonProperty("movieGenre") String movieGenre,
            @JsonProperty("movieGrade") String movieGrade,
            @JsonProperty("movieIntroduction") String movieIntroduction,
            @JsonProperty("movieImage") String movieImage,
            @JsonProperty("screeningStatus") String screeningStatus) {
        this.movieName = movieName;
        this.runningTime = runningTime;
        this.movieGenre = movieGenre;
        this.movieGrade = movieGrade;
        this.movieIntroduction = movieIntroduction;
        this.movieImage = movieImage;
        this.screeningStatus = screeningStatus;
    }
}
