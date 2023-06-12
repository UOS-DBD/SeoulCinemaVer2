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

        // 범죄도시3
        em.persist(Participant.builder()
                .participantName("마동석")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("윤계상")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("이상용")
                .participantType(ParticipantType.DIRECTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("에드코 필름")
                .participantType(ParticipantType.DISTRIBUTOR)
                .build());

        // 드림
        em.persist(Participant.builder()
                .participantName("박서준")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("아이유")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("이병헌")
                .participantType(ParticipantType.DIRECTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("플로스엠")
                .participantType(ParticipantType.DISTRIBUTOR)
                .build());

        // 더 퍼스트 슬램덩크
        em.persist(Participant.builder()
                .participantName("이노우에 다케히코")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("이노우에 다케히코")
                .participantType(ParticipantType.DIRECTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("도에히")
                .participantType(ParticipantType.DISTRIBUTOR)
                .build());

        // 가디언즈 오브 갤럭시 VOL.3
        em.persist(Participant.builder()
                .participantName("크리스 프랫")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("데이브 바티스타")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("카렌 길런")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("폼 클레멘티에프")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("빈 디젤")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("제임스 건")
                .participantType(ParticipantType.ACTOR)
                .build());


        em.persist(Participant.builder()
                .participantName("마블 스튜디오")
                .participantType(ParticipantType.DISTRIBUTOR)
                .build());

        // 라이드온
        em.persist(Participant.builder()
                .participantName("성룡")
                .participantType(ParticipantType.ACTOR)
                .build());


        em.persist(Participant.builder()
                .participantName("류 하오춘")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("래리 양")
                .participantType(ParticipantType.DIRECTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("상하이 필름 그룹")
                .participantType(ParticipantType.DISTRIBUTOR)
                .build());

        // 분노의 질주 라이드 오어 다이
        em.persist(Participant.builder()
                .participantName("빈 디젤")
                .participantType(ParticipantType.ACTOR)
                .build());


        em.persist(Participant.builder()
                .participantName("제이슨 모모아")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("제이슨 스타뎀")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("루이스 리터리어")
                .participantType(ParticipantType.DIRECTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("오리지널 필름")
                .participantType(ParticipantType.DISTRIBUTOR)
                .build());

        // 스즈메의 문단속
        em.persist(Participant.builder()
                .participantName("하라 나노카")
                .participantType(ParticipantType.ACTOR)
                .build());


        em.persist(Participant.builder()
                .participantName("마츠무라 호쿠토")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("신카이 마코토")
                .participantType(ParticipantType.DIRECTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("쇼박스")
                .participantType(ParticipantType.DISTRIBUTOR)
                .build());

        // 인어공주
        em.persist(Participant.builder()
                .participantName("할리 베일리")
                .participantType(ParticipantType.ACTOR)
                .build());


        em.persist(Participant.builder()
                .participantName("멜리사 맥카시")
                .participantType(ParticipantType.ACTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("롭 마샬")
                .participantType(ParticipantType.DIRECTOR)
                .build());

        em.persist(Participant.builder()
                .participantName("디즈니 스튜디오")
                .participantType(ParticipantType.DISTRIBUTOR)
                .build());


        em.flush();
        em.clear();
    }
}
