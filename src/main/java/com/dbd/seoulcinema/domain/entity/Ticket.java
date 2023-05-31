package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.TicketingStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "TICKET")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Ticket {

    @Id
    @Column(name = "TICKET_NUMBER")
    private String ticketNumber;

    @Column(name = "TICKETING_STATUS")
    private TicketingStatus ticketingStatus;

    @Column(name = "STANDARD_PRICE")
    private Long standardPrice;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_NUMBER")
    private */
}
