package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query("select m.movieName from Movie m, Schedule s "
            + "where m.movieNumber = s.movie.movieNumber "
            + "and s.scheduleNumber = :scheduleNumber")
    String findMovieNameByScheduleNumber(@Param("scheduleNumber") String scheduleNumber);
}
