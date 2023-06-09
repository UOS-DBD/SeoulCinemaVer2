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
    @Column(columnDefinition = "CHAR(1)")
    private String theaterNumber;

    @Column(columnDefinition = "CHAR(1)")
    private String theaterFloor;


    @Column(columnDefinition = "NUMBER(3)", nullable = false)
    @ColumnDefault("0")
    private Long seatQuantity;

}
