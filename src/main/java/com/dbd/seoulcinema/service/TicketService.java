package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.domain.entity.Ticket;
import com.dbd.seoulcinema.dto.ViewTicketsListDto;
import com.dbd.seoulcinema.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Optional<Ticket> viewTicketList(){

        List<ScheduleSeat> findTickets = ticketRepository.findAllByMember("mim501");
        // Optional<Ticket> mim501 = ticketRepository.findTicketByMember(userId);
        // Optional<Ticket> byId = ticketRepository.findById("202306010111");
        return null;
    }

    public String buyTicket(String clientId, String scheduleNumber, List<Long> seatNumber){

        return null;
    }
}
