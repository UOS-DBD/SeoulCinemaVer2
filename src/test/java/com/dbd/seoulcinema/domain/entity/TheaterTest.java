package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.TicketingStatus;
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
class TheaterTest {


    @PersistenceContext
    EntityManager em;

    @Test
    void injectSeat(){


        Theater theater = Theater.builder()
                .theaterNumber("1")
                .theaterFloor("1")
                .seatQuantity(300L)
                .build();

        em.persist(theater);
        em.flush();
        em.clear();
    }

    @Test
    void  selectSeat(){

        Member member = em.find(Member.class, "mim501");

        em.persist(Ticket.builder()
                .ticketingStatus(TicketingStatus.Y)
                .standardPrice(20000)
                .member(member)
                .build());
        em.flush();
    }
}