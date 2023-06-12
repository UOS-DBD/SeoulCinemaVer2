package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
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
class MovieTest {


    @PersistenceContext
    EntityManager em;

    @Test
    void injectMovieData(){

        em.persist(Movie.builder()
                .movieName("범죄도시3")
                .runningTime("02:08")
                .movieGenre(MovieGenre.ACTION)
                .movieGrade(MovieGrade.ADULT)
                .movieIntroduction("마동석 나오는 범죄자 잡는 영화입니다.")
                .movieImage("assets/img/범죄도시3.png")
                .screeningStatus(ScreeningStatus.Y)
                .build());
        em.persist(Movie.builder()
                .movieName("드림")
                .runningTime("01:46")
                .movieGenre(MovieGenre.COMEDY)
                .movieGrade(MovieGrade.ALL)
                .movieIntroduction("아이유 박서준이 나오는 노숙자 축구단 이야기입니다.")
                .movieImage("assets/img/드림.png")
                .screeningStatus(ScreeningStatus.Y)
                .build());
        em.persist(Movie.builder()
                .movieName("더 퍼스트 슬램덩크")
                .runningTime("02:04")
                .movieGenre(MovieGenre.DRAMA)
                .movieGrade(MovieGrade.ALL)
                .movieIntroduction("강백호의 농구 이야기입니다.")
                .movieImage("assets/img/더 퍼스트 슬램덩크.png")
                .screeningStatus(ScreeningStatus.Y)
                .build());
        em.persist(Movie.builder()
                .movieName("가디언즈 오브 갤럭시 VOL.3")
                .runningTime("03:02")
                .movieGenre(MovieGenre.SF)
                .movieGrade(MovieGrade.FIFTEEN)
                .movieIntroduction("‘가모라’를 잃고 슬픔에 빠져 있던 ‘피터 퀼’이 위기에 처한 은하계와 동료를 지키기 위해 다시 한번 가디언즈 팀과 힘을 모으고, 성공하지 못할 경우 그들의 마지막이 될지도 모르는 미션에 나서는 이야기")
                .movieImage("assets/img/가디언즈 오브 갤럭시 VOL. 3.png")
                .screeningStatus(ScreeningStatus.N)
                .build());
        em.persist(Movie.builder()
                .movieName("라이드 온")
                .runningTime("02:10")
                .movieGenre(MovieGenre.DRAMA)
                .movieGrade(MovieGrade.FIFTEEN)
                .movieIntroduction("한때 잘 나갔던 전설의 스턴트맨 ‘루오’(성룡) 유일한 파트너마 ‘레드 헤어’가 경매에 부쳐질 위기에 처하자 어쩔 수 없이 소원했던 딸 ‘바오’(류호존)에게 연락해 도움을 청한다.")
                .movieImage("assets/img/라이드 온.png")
                .screeningStatus(ScreeningStatus.Y)
                .build());
        em.persist(Movie.builder()
                .movieName("분노의 질주 라이드 오어 다이")
                .runningTime("01:58")
                .movieGenre(MovieGenre.ACTION)
                .movieGrade(MovieGrade.ADULT)
                .movieIntroduction("달리거나 죽거나, 그들의 마지막 질주가 시작된다!")
                .movieImage("assets/img/분노의 질주 라이드 오어 다이.png")
                .screeningStatus(ScreeningStatus.N)
                .build());
        em.persist(Movie.builder()
                .movieName("스즈메의 문단속")
                .runningTime("02:23")
                .movieGenre(MovieGenre.COMEDY)
                .movieGrade(MovieGrade.ALL)
                .movieIntroduction("문단속을 생활화합시다.")
                .movieImage("assets/img/스즈메의 문단속.png")
                .screeningStatus(ScreeningStatus.N)
                .build());
        em.persist(Movie.builder()
                .movieName("인어공주")
                .runningTime("02:40")
                .movieGenre(MovieGenre.SF)
                .movieGrade(MovieGrade.ALL)
                .movieIntroduction("언더더씨")
                .movieImage("assets/img/인어공주.png")
                .screeningStatus(ScreeningStatus.N)
                .build());

            em.flush();
            em.clear();
    }
}