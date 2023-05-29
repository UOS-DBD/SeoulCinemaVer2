package com.dbd.seoulcinema.global.utils;

import com.dbd.seoulcinema.domain.enumeration.ParticipantType;

import javax.persistence.Converter;

@Converter
public class ParticipantTypeConverter extends AbstractEnumAttributeConverter<ParticipantType> {

    public ParticipantTypeConverter() {
        super(ParticipantType.class,"관계자등급코드");
    }
}
