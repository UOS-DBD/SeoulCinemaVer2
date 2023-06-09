package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.TicketingStatus;

import javax.persistence.*;

import com.dbd.seoulcinema.vo.CreateTicketFinalVo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TICKET", indexes = {
    @Index(name = "idx_ticket_member",columnList = "MEMBER_NUMBER ASC"),
    @Index(name = "Idx_ticket_nonmember", columnList = "NONMEMBER_NUMBER ASC")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Ticket extends BaseTimeEntity {

    @Id
    @Column(length = 14)
    private String ticketNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 3, nullable = false)
    private TicketingStatus ticketingStatus;

    @Column(length = 10, nullable = false)
    private Integer standardPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NUMBER")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NONMEMBER_NUMBER")
    private NonMember nonMember;

    @Column(length = 100, nullable = false)
    private String movieName;

    @OneToMany(mappedBy = "ticket")
    private List<ScheduleSeat> scheduleSeats = new ArrayList<>(); //양방향 매핑

    public static Ticket makeMemberTicket(CreateTicketFinalVo vo, Member member, String movieName,
        List<ScheduleSeat> scheduleSeats) {

        return Ticket.builder()
            .ticketNumber(
                vo.getScheduleNumber() + vo.getSeats().get(0)) //티켓번호는 상영일정번호+좌석(첫 좌석= 인덱스 0 )번호
            .ticketingStatus(TicketingStatus.YES)
            .standardPrice(vo.getStandardPrice())
            .member(member)
            .scheduleSeats(scheduleSeats)
            .movieName(movieName)
            .build();
    }

    public static Ticket makeNonMemberTicket(CreateTicketFinalVo vo, NonMember nonmember,
        String movieName, List<ScheduleSeat> scheduleSeats) {

        return Ticket.builder()
            .ticketNumber(
                vo.getScheduleNumber() + vo.getSeats().get(0)) //티켓번호는 상영일정번호+좌석(첫 좌석= 인덱스 0 )번호
            .ticketingStatus(TicketingStatus.YES)
            .standardPrice(vo.getStandardPrice())
            .nonMember(nonmember)
            .scheduleSeats(scheduleSeats)
            .movieName(movieName)
            .build();
    }

    public void changeStatusWhenCancel() {
        this.ticketingStatus = TicketingStatus.NO;
    }
}
