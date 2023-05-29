package com.dbd.seoulcinema.domain.entity;

import javax.persistence.*;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import com.dbd.seoulcinema.global.utils.DiscountTypeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DISCOUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long discountNumber;

    @Column(name = "DISCOUNT_CODE")
    @Convert(converter = DiscountTypeConverter.class)
    private DiscountType discountType;

    @Column(name = "DISCOUNT_PRICE")
    private String discountPrice;
}
