package com.dbd.seoulcinema.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.dbd.seoulcinema.dto.ScreeningTimeDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "SCHEDULE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Schedule {

    @Id
    @Column(length = 10)
    private String scheduleNumber;

    @Column(columnDefinition = "DATE")
    private LocalDateTime screeningStartTime;

    @Column(columnDefinition = "DATE")
    private LocalDateTime screeningEndTime;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate screeningDate;


    @Column(columnDefinition = "NUMBER(2)")
    @ColumnDefault("1")
    private Integer screeningSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_NUMBER", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_NUMBER", nullable = false)
    private Theater theater;

    public void setContents(Movie movie, Theater theater, ScreeningTimeDto dto) {
        this.movie = movie;
        this.theater = theater;
        this.screeningDate = dto.getScreeningDate();
        this.screeningStartTime = dto.getScreeningStartTime();
        this.screeningEndTime = dto.getScreeningEndTime();
        this.screeningSession = dto.getScreeningSession();
    }
}
