package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
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

        em.persist(Theater.builder()
                .theaterNumber("1")
                .theaterFloor("1")
                .seatQuantity(300L)
                .build());

        em.persist(Theater.builder()
                .theaterNumber("2")
                .theaterFloor("1")
                .seatQuantity(300L)
                .build());

        em.persist(Theater.builder()
                .theaterNumber("3")
                .theaterFloor("2")
                .seatQuantity(300L)
                .build());

        em.persist(Theater.builder()
                .theaterNumber("4")
                .theaterFloor("2")
                .seatQuantity(300L)
                .build());

        em.persist(Theater.builder()
                .theaterNumber("5")
                .theaterFloor("2")
                .seatQuantity(300L)
                .build());

        em.persist(Theater.builder()
                .theaterNumber("6")
                .theaterFloor("3")
                .seatQuantity(300L)
                .build());

        em.persist(Theater.builder()
                .theaterNumber("7")
                .theaterFloor("3")
                .seatQuantity(300L)
                .build());

        em.persist(Theater.builder()
                .theaterNumber("8")
                .theaterFloor("3")
                .seatQuantity(300L)
                .build());


        em.flush();
        em.clear();
    }

    @Test
    void  selectSeat() {

        em.persist(Discount.builder()
                .discountType(DiscountType.ETC)
                .discountPrice(0)
                .build());

        em.flush();
    }


}