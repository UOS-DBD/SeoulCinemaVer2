package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ParticipantTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void injectParticipantData(){
        em.persist(Participant.builder()
                .participantName("마동석")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("윤계상")
                .participantType(ParticipantType.ACTOR)
                .build());
        em.flush();
        em.clear();
    }
}
