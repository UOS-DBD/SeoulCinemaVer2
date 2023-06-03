package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    @Query("SELECT s, m FROM Schedule s JOIN fetch s.movie m WHERE s.screeningDate = :date ")
    List<Object[]> findMovieAndSchedule(@Param("date") LocalDateTime date);

    @Query("SELECT s, m FROM Schedule s JOIN FETCH s.movie m " +
            "WHERE m.movieNumber = :movieNumber AND s.screeningDate = :screeningDate")
    List<Object[]> findMovieSchedule(@Param("movieNumber") Long movieNumber, @Param("screeningDate") LocalDateTime screeningDate);
}
