package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.ScheduleSeatId;
import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, ScheduleSeatId> {
    List<ScheduleSeat> findByScheduleNumber(Schedule schedule);

    @Query("select s from ScheduleSeat s "
            + "where s.scheduleNumber.scheduleNumber = :scheduleNumber "
            + "and s.seatNumber.seatNumber in (:seats)")
    List<ScheduleSeat> findAllByScheduleNumberAndSeats(@Param("scheduleNumber") String scheduleNumber,
                                                       @Param("seats") List<Long> seats);

    @Modifying
    @Query("delete from ScheduleSeat ss where ss.paymentStatus = 1 and ss.createdDate >= :fiveMinutesAgo")
    void deleteOldRecords(@Param("fiveMinutesAgo") LocalDateTime fiveMinutesAgo);

    @Query("select s from ScheduleSeat s "
            + "where s.ticket.ticketNumber = :ticketNumber")
    List<ScheduleSeat> findAllByTicketNumber(@Param("ticketNumber") String ticketNumber);
}
