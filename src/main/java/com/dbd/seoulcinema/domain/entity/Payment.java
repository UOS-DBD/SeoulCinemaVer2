package com.dbd.seoulcinema.domain.entity;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

import com.dbd.seoulcinema.domain.enumeration.PaymentType;
import com.dbd.seoulcinema.global.utils.PaymentTypeConverter;
import com.dbd.seoulcinema.vo.CreateTicketFinalVo;
import lombok.*;

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

    public static Payment makePayment(CreateTicketFinalVo vo, Ticket ticket, PaymentType paymentType) {
        return Payment.builder()
                .paymentNumber(UUID.randomUUID().toString())
                .paymentPrice(vo.getTotalPrice())
                .ticket(ticket)
                .paymentDate(LocalDate.now())
                .bankName(vo.getBankName())
                .paymentApproveNumber(UUID.randomUUID().toString())
                .paymentType(paymentType)
                .build();
    }
}
