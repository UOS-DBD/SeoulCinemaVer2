package com.dbd.seoulcinema.domain;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentDiscountId implements Serializable {

    private String payment;

    private Long discount;

    @Override
    public int hashCode() {
        return Objects.hash(payment,discount);
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
        return Objects.equals(this.payment, id.getPayment()) &&
            Objects.equals(this.discount, id.getDiscount());
    }
}
