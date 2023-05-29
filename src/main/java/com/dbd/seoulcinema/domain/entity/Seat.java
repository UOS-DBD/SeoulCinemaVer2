package com.dbd.seoulcinema.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEAT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Seat {

    @Id
    @Column(name = "SEAT_NUMBER")
    private String seatNumber;

    @Column(name = "ROW_NUMBER")
    private String rowNumber;

    @Column(name = "COL_NUMBER")
    private String colNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_NUMBER")
    private Theater theater;
}
