package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.dao.ViewSpecificPaymentDao;
import com.dbd.seoulcinema.domain.entity.Payment;
import com.dbd.seoulcinema.dto.ViewPaymentListDto;
import com.dbd.seoulcinema.dto.ViewSpecificPaymentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, String> {


    @Query("select distinct new com.dbd.seoulcinema.dto.ViewPaymentListDto(p.paymentNumber, t.movieName, p.paymentPrice, p.paymentDate, p.paymentStatus) "
            + "from Payment p, Ticket t "
            + "where p.ticket.ticketNumber = t.ticketNumber "
            + "and t.member.clientId = :clientId")
    List<ViewPaymentListDto> findPaymentsByMember(@Param("clientId") String clientId);


    @Query("select p from Payment p where p.ticket.ticketNumber = :ticketNumber and p.paymentStatus = 'YES'")
    Optional<Payment> findPaymentByTicketNumber(@Param("ticketNumber") String ticketNumber);


    @Query("select distinct new com.dbd.seoulcinema.dao.ViewSpecificPaymentDao(m.movieImage, t.movieName, t.ticketNumber," +
            " s.screeningStartTime, s.screeningEndTime, s.screeningDate, th.theaterFloor, t.standardPrice, p.paymentPrice, p.paymentPoint, p.bankName) "
            + "from Payment p, Ticket t, ScheduleSeat ss, Schedule s, Movie m, Theater th "
            + "where p.ticket.ticketNumber = t.ticketNumber "
            + "and t.ticketNumber = ss.ticket.ticketNumber "
            + "and ss.scheduleNumber = s.scheduleNumber "
            + "and s.movie.movieNumber = m.movieNumber "
            + "and s.theater.theaterNumber = th.theaterNumber "
            + "and p.paymentNumber =:paymentNumber")
    ViewSpecificPaymentDao findSpecificPaymentInfoById(@Param("paymentNumber") String paymentNumber);
}
