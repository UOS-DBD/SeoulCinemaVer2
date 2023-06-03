package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.theater.theaterNumber =  :theaterNumber")
    List<Seat> findTheaterNumber(@Param("theaterNumber") String theaterNumber);


    @Query("select s from Seat s, ScheduleSeat ss, Ticket t "
            + "where ss.ticket.ticketNumber = t.ticketNumber "
            + "and ss.seatNumber.seatNumber = s.seatNumber "
            + "and t.ticketNumber = :ticketNumber")
    List<Seat> findAllSeatsByTicketNumber(@Param("ticketNumber") String ticketNumber);
}
