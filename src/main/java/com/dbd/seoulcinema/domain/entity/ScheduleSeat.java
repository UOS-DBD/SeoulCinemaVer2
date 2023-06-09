package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.ScheduleSeatId;
import com.dbd.seoulcinema.domain.enumeration.PaymentStatus;

import java.util.Objects;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SCHEDULE_SEAT",indexes = {
    @Index(name = "idx_schedule_seat_payment",columnList = "SCHEDULE_NUMBER, paymentStatus ASC",unique = false),
    @Index(name = "idx_schedule_seat_ticket", columnList = "TICKET_NUMBER ASC")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@IdClass(ScheduleSeatId.class)
public class ScheduleSeat extends BaseTimeEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_NUMBER")
    private Seat seatNumber;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_NUMBER",columnDefinition = "VARCHAR2(10 CHAR)")
    private Schedule scheduleNumber;

    @Column(length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_NUMBER")
    private Ticket ticket;


    public void setTicketAndStatusWhenPayment(Ticket ticket) {
        this.paymentStatus = PaymentStatus.YES;
        this.ticket = ticket;
    }

}
