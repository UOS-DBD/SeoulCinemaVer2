package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Discount;
import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("select d from Discount d "
            + "where d.discountType = :discountType")
    public Discount findPointDiscountByDiscountType(@Param("discountType") DiscountType discountType);


    @Query("select distinct d from Discount d, Payment p, PaymentDiscount pd "
            + "where p.paymentNumber = pd.paymentNumber.paymentNumber "
            + "and pd.discountNumber.discountNumber = d.discountNumber "
            + "and p.paymentNumber = :paymentNumber")
    List<Discount> findAllDiscountsByPaymentNumber(@Param("paymentNumber")String paymentNumber);
}
