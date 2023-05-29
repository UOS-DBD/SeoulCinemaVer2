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
    @Column(name = "MOVIE_NUMBER")
    private Long movieMovie;

    @Column(name = "MOVIE_NAME", length = 30)
    private String movieName;

    @Column(name = "RUNNING_TIME", length = 5)
    private String runningTime;

    @Column(name = "MOVIE_GRNRE_CODE")
    @Convert(converter = MovieGenreConverter.class)
    private MovieGenre movieGenre;

    @Column(name = "MOVIE_GRADE_CODE")
    @Convert(converter = MovieGradeConverter.class)
    private MovieGrade movieGrade;

    @Column(name = "MOVIE_INTRODUCTION", length = 256)
    private String movieIntroduction;

    @Column(name = "MOVIE_IMAGE", length = 70)
    private String movieImage;

    @Column(name = "SCREENING_STATUS", length = 2)
    private ScreeningStatus screeningStatus;
}
