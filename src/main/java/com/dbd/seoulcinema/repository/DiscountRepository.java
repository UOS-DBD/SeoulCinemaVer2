package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Discount;
import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("select d from Discount d "
        + "where d.discountType = '07'")
    public Discount findDiscountByDiscountType();
}
