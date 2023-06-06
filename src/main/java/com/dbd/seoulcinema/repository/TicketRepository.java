package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Ticket;
import com.dbd.seoulcinema.dao.ViewSpecificTicketDao;
import com.dbd.seoulcinema.dto.ViewTicketsListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query("select distinct new com.dbd.seoulcinema.dto.ViewTicketsListDto(t.ticketNumber, m.movieName, s.screeningStartTime, s.screeningEndTime) "
            + "from Ticket t, ScheduleSeat ss, Schedule s, Movie m "
            + "where t.ticketNumber = ss.ticket.ticketNumber "
            + "and ss.scheduleNumber = s.scheduleNumber "
            + "and s.movie.movieName = m.movieName "
            + "and t.member.clientId = :clientId "
            + "and t.ticketingStatus = 'YES'")
    List<ViewTicketsListDto> findTicketListByMember(@Param("clientId") String clientId); //--> cross join 발생함! 추후 수정 예정


    @Query("select new com.dbd.seoulcinema.dto.ViewTicketsListDto(t.ticketNumber, m.movieName, s.screeningStartTime, s.screeningEndTime) "
            + "from Ticket t, ScheduleSeat ss, Schedule s, Movie m "
            + "where t.ticketNumber = ss.ticket.ticketNumber "
            + "and ss.scheduleNumber = s.scheduleNumber "
            + "and s.movie.movieName = m.movieName "
            + "and t.nonMember.phoneNumber = :clientId "
            + "and t.ticketingStatus = 'YES'")
    List<ViewTicketsListDto> findTicketListByNonMember(@Param("clientId") String clientId); //--> cross join 발생함! 추후 수정 예정

    @Query("select distinct new com.dbd.seoulcinema.dao.ViewSpecificTicketDao(m.movieImage, m.movieName, s.screeningStartTime, " +
            "s.screeningEndTime, s.screeningDate, th.theaterFloor) "
            + "from Ticket t, ScheduleSeat ss, Schedule s, Movie m, Theater th "
            + "where t.ticketNumber = ss.ticket.ticketNumber "
            + "and ss.scheduleNumber = s.scheduleNumber "
            + "and s.movie.movieNumber = m.movieNumber "
            + "and s.theater.theaterNumber = th.theaterNumber "
            + "and t.ticketNumber = :ticketNumber")
    ViewSpecificTicketDao findSpecificTicketInfoById(@Param("ticketNumber") String ticketNumber);

    Optional<Ticket> findById(String number);

}
