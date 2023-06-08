package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;

import javax.persistence.*;

import com.dbd.seoulcinema.global.utils.MovieGenreConverter;
import com.dbd.seoulcinema.global.utils.MovieGradeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOVIE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long movieNumber;

    @Column(length = 100)
    private String movieName;

    @Column(length = 5)
    private String runningTime;

    @Convert(converter = MovieGenreConverter.class)
    private MovieGenre movieGenre;

    @Convert(converter = MovieGradeConverter.class)
    private MovieGrade movieGrade;

    @Column(length = 256)
    private String movieIntroduction;

    @Column(length = 70)
    private String movieImage;

    @Column(length = 2)
    private ScreeningStatus screeningStatus;

    public void update(String movieName, String runningTime, MovieGenre movieGenre, MovieGrade movieGrade, String movieIntroduction, String movieImage, ScreeningStatus screeningStatus) {
        this.movieName = movieName;
        this.runningTime = runningTime;
        this.movieGenre = movieGenre;
        this.movieGrade = movieGrade;
        this.movieIntroduction = movieIntroduction;
        this.movieImage = movieImage;
        this.screeningStatus =screeningStatus;
    }
}
