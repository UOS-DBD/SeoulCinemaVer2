package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.ScheduleSeatId;
import com.dbd.seoulcinema.domain.entity.Member;
import com.dbd.seoulcinema.domain.entity.Payment;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.domain.entity.Ticket;
import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import com.dbd.seoulcinema.domain.enumeration.PaymentType;
import com.dbd.seoulcinema.repository.*;
import com.dbd.seoulcinema.vo.CreateTicketFinalVo;
import com.dbd.seoulcinema.vo.CreateTicketVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final MemberRepository memberRepository;

    private final ScheduleRepository scheduleRepository;

    private final ScheduleSeatRepository scheduleSeatRepository;

    private final PaymentRepository paymentRepository;
    @Transactional
    public Optional<Ticket> viewTicketList() {

        List<ScheduleSeat> findTickets = ticketRepository.findAllByMember("mim501");
        // Optional<Ticket> mim501 = ticketRepository.findTicketByMember(userId);
        // Optional<Ticket> byId = ticketRepository.findById("202306010111");
        return null;
    }

    public String buyTicket(String clientId, String scheduleNumber, List<Long> seatNumber) {

        return null;
    }

    public Integer calculateTotalPrice(Integer seatCount, DiscountType discountType, Integer point) {

        return 12000 * seatCount - discountType.getAmount() - point;
    }

    @Transactional
    public String makeTicketsAndPayment(CreateTicketFinalVo vo, String memberId, PaymentType paymentType) {

        Member findMember = memberRepository.findById(memberId).get();
        System.out.println("vo스케줄 넘버"+vo.getScheduleNumber());
        String movieName = scheduleRepository.findMovieNameByScheduleNumber(vo.getScheduleNumber());
        System.out.println("영화 이름"+movieName);
        List<ScheduleSeat> findScheduleSeats = scheduleSeatRepository.
                findAllByScheduleNumberAndSeats(vo.getScheduleNumber(), vo.getSeats());
        System.out.println("크기!!!!!"+findScheduleSeats.size());
        //티켓 엔티티 생성
        Ticket ticket = Ticket.makeTicket(vo, findMember, movieName,findScheduleSeats);
        findScheduleSeats.forEach(s ->s.setTicket(ticket));
        ticketRepository.save(ticket);

        //결제 엔티티 생성
        Payment payment = Payment.makePayment(vo, ticket, paymentType);
        paymentRepository.save(payment);

        return "success";
    }
}
