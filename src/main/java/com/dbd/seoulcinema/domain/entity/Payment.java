package com.dbd.seoulcinema.domain.entity;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

import com.dbd.seoulcinema.domain.enumeration.PaymentStatus;
import com.dbd.seoulcinema.domain.enumeration.PaymentType;
import com.dbd.seoulcinema.global.utils.PaymentTypeConverter;
import com.dbd.seoulcinema.vo.CreateTicketFinalVo;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Payment {

    @Id
    @Column(length = 36)
    private String paymentNumber;


    private Integer paymentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_NUMBER")
    private Ticket ticket;


    private LocalDate paymentDate;

    @Column(length = 14)
    private String bankName;

    @Column(length = 36)
    private String paymentApproveNumber;


    @Convert(converter = PaymentTypeConverter.class)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ColumnDefault("0")
    private Integer paymentPoint;

    public static Payment makePaymentCard(CreateTicketFinalVo vo, Ticket ticket, PaymentType paymentType) {
        return Payment.builder()
                .paymentNumber(UUID.randomUUID().toString())
                .paymentPrice(vo.getTotalPrice())
                .ticket(ticket)
                .paymentDate(LocalDate.now())
                .bankName(vo.getBankName())
                .paymentApproveNumber(UUID.randomUUID().toString())
                .paymentType(paymentType)
                .paymentStatus(PaymentStatus.YES)
                .paymentPoint(vo.getPoint())
                .bankName(null)
                .build();
    }

    public static Payment makePayment(CreateTicketFinalVo vo, Ticket ticket, PaymentType paymentType) {
        return Payment.builder()
                .paymentNumber(UUID.randomUUID().toString())
                .paymentPrice(vo.getTotalPrice())
                .ticket(ticket)
                .paymentDate(LocalDate.now())
                .bankName(vo.getBankName())
                .paymentType(paymentType)
                .paymentStatus(PaymentStatus.YES)
                .paymentPoint(vo.getPoint())
                .bankName(vo.getBankName())
                .build();
    }

    public void changeStatusWhenCancel(){
        this.paymentStatus=PaymentStatus.NO;
    }
}
