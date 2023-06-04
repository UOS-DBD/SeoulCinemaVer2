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
                .birth(LocalDate.now())
                .build());
       em.flush();
    }
}