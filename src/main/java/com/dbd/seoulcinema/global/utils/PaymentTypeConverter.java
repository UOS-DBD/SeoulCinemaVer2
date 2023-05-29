package com.dbd.seoulcinema.global.utils;

import com.dbd.seoulcinema.domain.enumeration.PaymentType;

import javax.persistence.Converter;

@Converter
public class PaymentTypeConverter extends AbstractEnumAttributeConverter<PaymentType> {

    public PaymentTypeConverter() {
        super(PaymentType.class,"결제종류코드");
    }
}
