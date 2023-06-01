package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.ScheduleSeatId;
import com.dbd.seoulcinema.domain.enumeration.PaymentStatus;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SCHEDULE_SEAT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@IdClass(ScheduleSeatId.class)
public class ScheduleSeat extends BaseTimeEntity{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_NUMBER")
    private Seat seatNumber;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_NUMBER")
    private Schedule scheduleNumber;

    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_NUMBER")
    private Ticket ticket;

    //티켓 생성할때 사용되는 연관관계 편의 메소드
    public void setTicket(Ticket ticket){
        if(this.ticket != null){
            this.ticket.getScheduleSeats().remove(this);
        }

        this.ticket=ticket;
        ticket.getScheduleSeats().add(this);
    }
}
