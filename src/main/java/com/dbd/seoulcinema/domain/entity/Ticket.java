package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.TicketingStatus;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TICKET")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Ticket extends BaseTimeEntity {

    @Id
    @Column(name = "TICKET_NUMBER")
    private String ticketNumber;

    @Column(name = "TICKETING_STATUS")
    private TicketingStatus ticketingStatus;

    @Column(name = "STANDARD_PRICE")
    private Integer standardPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_NUMBER")
    private Member member;

    @Column(name = "MOVIE_NAME")
    private String movieName;

    @OneToMany(mappedBy = "ticket")
    private List<ScheduleSeat> scheduleSeats = new ArrayList<>();


}
