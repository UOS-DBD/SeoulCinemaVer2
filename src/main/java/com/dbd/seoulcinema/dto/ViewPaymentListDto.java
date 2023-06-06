package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.enumeration.PaymentStatus;
import com.dbd.seoulcinema.domain.enumeration.TicketingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewPaymentListDto {

    private String paymentNumber;

    private String movieName;

    private Integer totalPrice;

    private LocalDate screeningDate;

    private TicketingStatus paymentStatus;
}
