package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.ScheduleSeatId;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, ScheduleSeatId> {

    @Query("select s from ScheduleSeat s "
            + "where s.scheduleNumber.scheduleNumber = :scheduleNumber "
            + "and s.seatNumber.seatNumber in (:seats)")
    List<ScheduleSeat> findAllByScheduleNumberAndSeats(@Param("scheduleNumber") String scheduleNumber,
                                                       @Param("seats") List<Long> seats);


}