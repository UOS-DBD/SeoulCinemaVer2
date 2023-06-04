package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.PaymentDiscountId;
import com.dbd.seoulcinema.domain.entity.PaymentDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDiscountRepository extends JpaRepository<PaymentDiscount, PaymentDiscountId> {

}
