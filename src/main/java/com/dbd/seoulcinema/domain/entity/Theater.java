package com.dbd.seoulcinema.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "THEATER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Theater {

    @Id
    @Column(name = "THEATER_NUMBER")
    private String theaterNumber;


    @Column(name = "THEATER_FLOOR")
    private String theaterFloor;


    @Column(name = "SEAT_QUANTITY")
    @ColumnDefault("0")
    private Long seatQuantity;

}
