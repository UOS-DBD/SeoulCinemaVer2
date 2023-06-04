package com.dbd.seoulcinema.domain.entity;

import javax.persistence.*;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import com.dbd.seoulcinema.global.utils.DiscountTypeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DISCOUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long discountNumber;


    @Convert(converter = DiscountTypeConverter.class)
    private DiscountType discountType;


    private Integer discountPrice;
}
