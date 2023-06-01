package com.dbd.seoulcinema.domain.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class ScheduleTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void injectSchedule(){

        Movie movie = em.find(Movie.class, 1L);
        Theater theater = em.find(Theater.class, "1");

        em.persist(Schedule.builder()
                .scheduleNumber("20230601011")
                .screeningStartTime(LocalDateTime.now())
                .screeningEndTime(LocalDateTime.now())
                .screeningDate(LocalDateTime.now())
                .screeningSession(1)
                .movie(movie)
                .theater(theater)
                .build());
        em.flush();
    }
}