package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.PaymentDiscountId;
import java.io.Serializable;
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
@Table(name = "PAYMENT_DISCOuNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@IdClass(PaymentDiscountId.class)
public class PaymentDiscount implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_NUMBER",columnDefinition = "VARCHAR2(36)")
    //payment 테이블과 PK 타입이 약간 다름 > 에러나면 확인필요~!
    private Payment paymentNumber;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISCOUNT_NUMBER")
    private Discount discountNumber;
}
