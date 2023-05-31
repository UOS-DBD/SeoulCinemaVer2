package com.dbd.seoulcinema.domain.entity;

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
}