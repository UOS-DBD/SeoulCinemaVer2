package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    @Query("SELECT s, m FROM Schedule s JOIN fetch s.movie m WHERE s.screeningDate = :date ")
    List<Object[]> findMovieAndSchedule(@Param("date") LocalDate date);

    @Query("SELECT s, m FROM Schedule s JOIN FETCH s.movie m " +
            "WHERE m.movieNumber = :movieNumber AND s.screeningDate = :screeningDate")
    List<Object[]> findMovieSchedule(@Param("movieNumber") Long movieNumber, @Param("screeningDate") LocalDate screeningDate);

    @Query("select m.movieName from Movie m, Schedule s "
            + "where m.movieNumber = s.movie.movieNumber "
            + "and s.scheduleNumber = :scheduleNumber")
    String findMovieNameByScheduleNumber(@Param("scheduleNumber") String scheduleNumber);
}
