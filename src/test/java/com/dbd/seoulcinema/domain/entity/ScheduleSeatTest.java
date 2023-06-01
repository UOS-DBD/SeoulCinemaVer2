package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
@Rollback(value = false)
class ScheduleSeatTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void injectScheduleSeat(){

        Schedule schedule = em.find(Schedule.class, "20230601011");
        Seat seat = em.find(Seat.class, 1L);
        Ticket ticket = em.find(Ticket.class, "202306010111");

        em.persist(ScheduleSeat.builder()
                .scheduleNumber(schedule)
                .seatNumber(seat)
                .paymentStatus(PaymentStatus.Y)
                .ticket(ticket)
                .build());
    }
}