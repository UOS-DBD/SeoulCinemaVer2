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
    @Column(length = 11)
    private String scheduleNumber;

    @Column(columnDefinition = "DATE")
    private LocalDateTime screeningStartTime;

    @Column(columnDefinition = "DATE")
    private LocalDateTime screeningEndTime;

    @Column(columnDefinition = "DATE")
    private LocalDate screeningDate;


    @ColumnDefault("0")
    private Integer screeningSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_NUMBER")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_NUMBER")
    private Theater theater;
}
