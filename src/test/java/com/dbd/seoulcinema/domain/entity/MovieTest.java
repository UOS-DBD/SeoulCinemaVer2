package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class MovieTest {


    @PersistenceContext
    EntityManager em;

    @Test
    void injectMovieData(){

        em.persist(Movie.builder()
                .movieName("영화1")
                .runningTime("02:30")
                .movieGenre(MovieGenre.SF)
                .movieGrade(MovieGrade.ADULT)
                .movieIntroduction("영화1설명")
                .movieImage("static/img/영화1.png")
                .screeningStatus(ScreeningStatus.SHOWING)
                .build());
        em.persist(Movie.builder()
                .movieName("영화2")
                .runningTime("01:30")
                .movieGenre(MovieGenre.SF)
                .movieGrade(MovieGrade.ALL)
                .movieIntroduction("영화2설명")
                .movieImage("static/img/영화2.png")
                .screeningStatus(ScreeningStatus.SHOWING)
                .build());
        em.persist(Movie.builder()
                .movieName("영화3")
                .runningTime("02:00")
                .movieGenre(MovieGenre.ACTION)
                .movieGrade(MovieGrade.ALL)
                .movieIntroduction("영화3설명")
                .movieImage("static/img/영화3.png")
                .screeningStatus(ScreeningStatus.SHOWING)
                .build());
        em.persist(Movie.builder()
                .movieName("영화4")
                .runningTime("01:45")
                .movieGenre(MovieGenre.COMEDY)
                .movieGrade(MovieGrade.ADULT)
                .movieIntroduction("영화4설명")
                .movieImage("static/img/영화4.png")
                .screeningStatus(ScreeningStatus.SHOWING_DONE)
                .build());
        em.persist(Movie.builder()
                .movieName("영화5")
                .runningTime("02:00")
                .movieGenre(MovieGenre.DRAMA)
                .movieGrade(MovieGrade.FIFTEEN)
                .movieIntroduction("영화5설명")
                .movieImage("static/img/영화5.png")
                .screeningStatus(ScreeningStatus.SHOWING)
                .build());
        em.persist(Movie.builder()
                .movieName("영화6")
                .runningTime("02:10")
                .movieGenre(MovieGenre.DRAMA)
                .movieGrade(MovieGrade.FIFTEEN)
                .movieIntroduction("영화6설명")
                .movieImage("static/img/영화6.png")
                .screeningStatus(ScreeningStatus.SHOWING_DONE)
                .build());
        em.persist(Movie.builder()
                .movieName("영화7")
                .runningTime("03:00")
                .movieGenre(MovieGenre.EROTIC)
                .movieGrade(MovieGrade.ADULT)
                .movieIntroduction("영화7설명")
                .movieImage("static/img/영화7.png")
                .screeningStatus(ScreeningStatus.SHOWING)
                .build());
        em.persist(Movie.builder()
                .movieName("영화8")
                .runningTime("02:50")
                .movieGenre(MovieGenre.ACTION)
                .movieGrade(MovieGrade.ALL)
                .movieIntroduction("영화8설명")
                .movieImage("static/img/영화8.png")
                .screeningStatus(ScreeningStatus.SHOWING)
                .build());

        em.flush();
        em.clear();
    }
}