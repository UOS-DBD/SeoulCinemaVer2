package com.dbd.seoulcinema.global.utils;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;

import javax.persistence.Converter;

@Converter
public class DiscountTypeConverter extends AbstractEnumAttributeConverter<DiscountType> {

    public DiscountTypeConverter() {
        super(DiscountType.class, "할인코드");
    }
}
