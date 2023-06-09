package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.dto.MovieAndSchedulesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    @Query("SELECT new com.dbd.seoulcinema.dto.MovieAndSchedulesDto(m.movieNumber, m.movieName," +
            " s.scheduleNumber, s.screeningStartTime, s.screeningEndTime, s.screeningDate," +
            " t.theaterNumber, t.theaterFloor, t.seatQuantity)" +
            " FROM Schedule s JOIN s.movie m JOIN s.theater t " +
            "WHERE s.scheduleNumber = :scheduleNumber")
    MovieAndSchedulesDto findMovieAndSchedule(@Param("scheduleNumber") String scheduleNumber);

    @Query("SELECT new com.dbd.seoulcinema.dto.MovieAndSchedulesDto(m.movieNumber, m.movieName," +
            " s.scheduleNumber, s.screeningStartTime, s.screeningEndTime, s.screeningDate," +
            " t.theaterNumber, t.theaterFloor, t.seatQuantity)" +
            " FROM Schedule s JOIN s.movie m JOIN s.theater t " +
            "WHERE s.screeningDate = :date")
    List<MovieAndSchedulesDto> findMovieAndSchedules(@Param("date") LocalDate date);

    @Query("SELECT new com.dbd.seoulcinema.dto.MovieAndSchedulesDto(m.movieNumber, m.movieName," +
            " s.scheduleNumber, s.screeningStartTime, s.screeningEndTime, s.screeningDate," +
            " t.theaterNumber, t.theaterFloor, t.seatQuantity)" +
            "FROM Schedule s JOIN s.movie m JOIN s.theater t " +
            "WHERE m.movieNumber = :movieNumber AND s.screeningDate = :screeningDate")
    List<MovieAndSchedulesDto> findMovieSchedules(@Param("movieNumber") Long movieNumber, @Param("screeningDate") LocalDate screeningDate);

    @Query("SELECT new com.dbd.seoulcinema.dto.MovieAndSchedulesDto(m.movieNumber, m.movieName," +
            " s.scheduleNumber, s.screeningStartTime, s.screeningEndTime, s.screeningDate," +
            " t.theaterNumber, t.theaterFloor, t.seatQuantity)" +
            "FROM Schedule s JOIN s.movie m JOIN s.theater t ")
    List<MovieAndSchedulesDto> findAllMovieSchedules();

    @Query("select m.movieName from Movie m, Schedule s "
            + "where m.movieNumber = s.movie.movieNumber "
            + "and s.scheduleNumber = :scheduleNumber")
    String findMovieNameByScheduleNumber(@Param("scheduleNumber") String scheduleNumber);
}
