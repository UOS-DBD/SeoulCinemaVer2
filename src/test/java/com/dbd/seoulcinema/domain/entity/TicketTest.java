package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.TicketingStatus;
import com.dbd.seoulcinema.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class TicketTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TicketRepository ticketRepository;

    @Test
    void injectTicket(){

        Member member = em.find(Member.class, "mim501");
        em.persist(Ticket.builder()
                .ticketNumber("202306010111")
                .ticketingStatus(TicketingStatus.YES)
                .standardPrice(10000)
                .member(member)
                .movieName("범죄도시3")
                .build());

    }
}