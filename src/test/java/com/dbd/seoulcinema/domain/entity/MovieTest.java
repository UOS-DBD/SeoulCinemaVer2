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
                .movieIntroduction("아이엠그루트")
                .movieImage("assets/img/가디언즈 오브 갤럭시 VOL. 3.png")
                .screeningStatus(ScreeningStatus.N)
                .build());
        em.persist(Movie.builder()
                .movieName("라이드 온")
                .runningTime("02:10")
                .movieGenre(MovieGenre.DRAMA)
                .movieGrade(MovieGrade.FIFTEEN)
                .movieIntroduction("무슨 영화인지 모릅니다.")
                .movieImage("assets/img/라이드 온.png")
                .screeningStatus(ScreeningStatus.Y)
                .build());
        em.persist(Movie.builder()
                .movieName("분노의 질주 라이드 오어 다이")
                .runningTime("01:58")
                .movieGenre(MovieGenre.ACTION)
                .movieGrade(MovieGrade.ADULT)
                .movieIntroduction("분노의 질주 시리즈로 빡빡이 형님이 나옵니다.")
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