package com.dbd.seoulcinema.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DiscountTest {


    @PersistenceContext
    EntityManager em;

    @Test
    public void injectDiscount() {

        em.persist(Discount.builder()
            .discountType(DiscountType.KB)
            .discountPrice(1000)
            .build());

        em.persist(Discount.builder()
            .discountType(DiscountType.SAMSUNG)
            .discountPrice(2000)
            .build());

        em.persist(Discount.builder()
            .discountType(DiscountType.TOSS)
            .discountPrice(3000)
            .build());

        em.persist(Discount.builder()
            .discountType(DiscountType.SHINHAN)
            .discountPrice(3500)
            .build());

        em.persist(Discount.builder()
            .discountType(DiscountType.SKT)
            .discountPrice(1000)
            .build());

        em.persist(Discount.builder()
            .discountType(DiscountType.KT)
            .discountPrice(1000)
            .build());

        em.persist(Discount.builder()
            .discountType(DiscountType.POINTDISCOUNT)
            .discountPrice(0)
            .build());

        em.persist(Discount.builder()
            .discountType(DiscountType.BC)
            .discountPrice(0)
            .build());

        em.persist(Discount.builder()
            .discountType(DiscountType.WOORI)
            .discountPrice(0)
            .build());

        em.flush();
    }
}