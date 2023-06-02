package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import com.dbd.seoulcinema.dto.MovieDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // movie 상세 정보 조회
    @Query("SELECT new com.dbd.seoulcinema.dto.MovieDetailDto(" +
            "m.movieNumber, m.movieName, m.runningTime, m.movieGenre, m.movieGrade, m.movieIntroduction, m.movieImage, m.screeningStatus, " +
            "p.participantNumber, p.participantName, p.participantType) " +
            "FROM Movie m " +
            "INNER JOIN ParticipantMovie pm " +
            "ON m.movieNumber = pm.movieNumber " +
            "INNER JOIN Participant p " +
            "ON pm.participantNumber = p.participantNumber " +
            "WHERE m.movieNumber = :movieNumber")
    public List<MovieDetailDto> findMovieDetail(@Param("movieNumber") Long movieNumber);
}
