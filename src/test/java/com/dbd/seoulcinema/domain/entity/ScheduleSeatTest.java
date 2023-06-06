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
        Seat seat1 = em.find(Seat.class, 9L);
        Seat seat2 = em.find(Seat.class, 10L);
       // Ticket ticket = em.find(Ticket.class, "202306010111");

        em.persist(ScheduleSeat.builder()
                .scheduleNumber(schedule)
                .seatNumber(seat1)
                .paymentStatus(PaymentStatus.YES)
                .ticket(null)
                .build());
        em.persist(ScheduleSeat.builder()
                .scheduleNumber(schedule)
                .seatNumber(seat2)
                .paymentStatus(PaymentStatus.YES)
                .ticket(null)
                .build());
    }
}