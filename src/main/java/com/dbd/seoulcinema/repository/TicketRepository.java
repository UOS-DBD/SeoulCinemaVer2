package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Member;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.domain.entity.Ticket;
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
            + "and t.member.clientId = :clientId")
    List<ViewTicketsListDto> findTicketListByMember(@Param("clientId") String clientId); //--> cross join 발생함! 추후 수정 예정


    @Query("select new com.dbd.seoulcinema.dto.ViewTicketsListDto(t.ticketNumber, m.movieName, s.screeningStartTime, s.screeningEndTime) "
            + "from Ticket t, ScheduleSeat ss, Schedule s, Movie m "
            + "where t.ticketNumber = ss.ticket.ticketNumber "
            + "and ss.scheduleNumber = s.scheduleNumber "
            + "and s.movie.movieName = m.movieName "
            + "and t.nonMember.phoneNumber = :clientId")
    List<ViewTicketsListDto> findTicketListByNonMember(@Param("clientId") String clientId); //--> cross join 발생함! 추후 수정 예정


    @Query("select ss "
            + "from Ticket t, ScheduleSeat ss, Schedule s "
            + "where t.ticketNumber = ss.ticket.ticketNumber "
            + "and ss.scheduleNumber = s.scheduleNumber "
            + "and t.member.clientId = :memberId")
    List<ScheduleSeat> findAllByMember(@Param("memberId") String memberId);

   /* @Query("select t from Ticket t, Member m where t.member.clientId = m.clientId and m.clientId = :memberId")
    Optional<Ticket> findTicketByMember(@Param("memberId") String memberId); */

    Optional<Ticket> findById(String number);

}
