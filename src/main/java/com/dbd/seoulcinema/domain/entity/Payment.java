package com.dbd.seoulcinema.domain.entity;

import java.time.LocalDate;
import javax.persistence.*;

import com.dbd.seoulcinema.domain.enumeration.PaymentType;
import com.dbd.seoulcinema.global.utils.PaymentTypeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PAYMENT_NUMBER", length = 36)
    private String paymentNumber;

    @Column(name = "PAYMENT_PRICE")
    private Long paymentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_NUMBER")
    private Ticket ticket;

    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;

    @Column(name = "BANK_NAME", length = 14)
    private String bankName;

    @Column(name = "PAYMENT_APPROVE_NUMBER", length = 36)
    private String paymentApproveNumber;

    @Column(name = "PAYMENT_TYPE_CODE")
    @Convert(converter = PaymentTypeConverter.class)
    private PaymentType paymentType;
}
