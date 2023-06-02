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
    @Query("SELECT new com.dbd.seoulcinema.dto.MovieAndSchedulesDto(m.movieNumber, m.movieName, m.movieGrade, s.scheduleNumber, s.screeningStartTime, s.screeningEndTime, s.screeningDate)" +
            " FROM Schedule s JOIN s.movie m WHERE s.screeningDate = :date ")
    List<MovieAndSchedulesDto> findMovieAndSchedules(@Param("date") LocalDate date);

    @Query("SELECT s FROM Schedule s JOIN s.movie m " +
            "WHERE m.movieNumber = :movieNumber AND s.screeningDate = :screeningDate")
    List<Schedule> findMovieSchedules(@Param("movieNumber") Long movieNumber, @Param("screeningDate") LocalDate screeningDate);
}
