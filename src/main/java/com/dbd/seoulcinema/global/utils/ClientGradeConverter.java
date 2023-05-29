package com.dbd.seoulcinema.global.utils;

import com.dbd.seoulcinema.domain.enumeration.ClientGrade;

import javax.persistence.Converter;

@Converter
public class ClientGradeConverter extends AbstractEnumAttributeConverter<ClientGrade>{

    public ClientGradeConverter() {
        super(ClientGrade.class,"고객등급코드");
    }
}
