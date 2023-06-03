package com.dbd.seoulcinema.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "SEAT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SEAT_NUMBER")
    private Long seatNumber;

    @Column(name = "ROW_NUMBER")
    private String rowNumber;

    @Column(name = "COL_NUMBER")
    private String colNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_NUMBER")
    private Theater theater;

    public static String getSeatLocation(List<Seat> seats){
        String location = "";

        for(int i=0;i<seats.size();i++){

            location+=seats.get(i).rowNumber+seats.get(i).colNumber;

            if(!(i==seats.size()-1))
                location+="/ ";
        }
        return location;
    }
}
