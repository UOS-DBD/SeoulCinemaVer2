package com.dbd.seoulcinema.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentDiscountId implements Serializable {


    private String paymentNumber;


    private Long discountNumber;

    @Override
    public int hashCode() {
        return Objects.hash(paymentNumber,discountNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        PaymentDiscountId id = (PaymentDiscountId) obj;
        return Objects.equals(this.paymentNumber, id.getPaymentNumber()) &&
            Objects.equals(this.discountNumber, id.getDiscountNumber());
    }
}
