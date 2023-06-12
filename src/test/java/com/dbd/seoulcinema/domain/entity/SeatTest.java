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
class SeatTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void injectTheaterAndSeat() {


        for(int theaterNumber = 1; theaterNumber <= 8 ; theaterNumber++){
            Theater theater = em.find(Theater.class, Integer.toString(theaterNumber));

            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("A")
                        .colNumber(""+i)
                        .build());
            }

            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("B")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("C")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("D")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("E")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("F")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("G")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("H")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("I")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("J")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("K")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("L")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("M")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("N")
                        .colNumber(""+i)
                        .build());
            }
            for(int i=1; i<=20; i++){
                em.persist(Seat.builder()
                        .theater(theater)
                        .rowNumber("O")
                        .colNumber(""+i)
                        .build());
            }
        }



        em.flush();
        em.clear();
    }
}