package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.ClientGrade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void injectMember(){

       em.persist(Member.builder()
                .clientId("mim501")
                .password("1234")
                .phoneNumber("010-3370-1111")
                .point(20000L)
                .clientGrade(ClientGrade.NORMAL)
                .birth(LocalDate.of(1998, 03, 03))
                .build());

       em.persist(Member.builder()
                .clientId("na123")
                .password("4321")
                .phoneNumber("010-1234-1234")
                .point(13000L)
                .clientGrade(ClientGrade.VIP)
                .birth(LocalDate.of(1999, 04, 01))
                .build());

        em.persist(Member.builder()
                .clientId("jinsu11")
                .password("1234")
                .phoneNumber("010-1577-1577")
                .point(3000L)
                .clientGrade(ClientGrade.VIP)
                .birth(LocalDate.of(1999, 01, 04))
                .build());

       em.flush();
    }
}