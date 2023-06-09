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
@Table(name = "MOVIE" , indexes = {
    @Index(name = "idx_movie_name", columnList = "movieName ASC"),
    @Index(name = "idx_movie_screening_status", columnList = "screeningStatus ASC")}
    )
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long movieNumber;

    @Column(length = 100, nullable = false)
    private String movieName;

    @Column(columnDefinition = "CHAR(5)", nullable = false)
    private String runningTime;

    @Convert(converter = MovieGenreConverter.class)
    @Column(columnDefinition = "CHAR(2)", nullable = false)
    private MovieGenre movieGenre;

    @Convert(converter = MovieGradeConverter.class)
    @Column(columnDefinition = "CHAR(2)", nullable = false)
    private MovieGrade movieGrade;

    @Column(length = 256)
    private String movieIntroduction;

    @Column(length = 70)
    private String movieImage;

    @Column(columnDefinition = "CHAR(1)", nullable = false)
    @Enumerated(EnumType.STRING)
    private ScreeningStatus screeningStatus;

    public void update(String movieName, String runningTime, MovieGenre movieGenre,
        MovieGrade movieGrade, String movieIntroduction, String movieImage,
        ScreeningStatus screeningStatus) {
        this.movieName = movieName;
        this.runningTime = runningTime;
        this.movieGenre = movieGenre;
        this.movieGrade = movieGrade;
        this.movieIntroduction = movieIntroduction;
        this.movieImage = movieImage;
        this.screeningStatus = screeningStatus;
    }
}
