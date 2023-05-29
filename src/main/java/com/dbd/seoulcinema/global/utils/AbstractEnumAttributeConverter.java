package com.dbd.seoulcinema.global.utils;

import com.dbd.seoulcinema.domain.enumeration.CommonDescCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeConverter;
@AllArgsConstructor
@NoArgsConstructor
public class AbstractEnumAttributeConverter<E extends Enum<E> & CommonDescCode>
        implements AttributeConverter<E, String> {

    private Class<E> targetEnumClass;

    private String enumName;

    @Override
    public String convertToDatabaseColumn(E enumType) {
        if (enumType == null) {
            throw new IllegalArgumentException(String.format("%s는 NULL으로 저장할 수 없습니다", enumName));
        }
        return EnumCodeConverterUtils.getCodeFromEnum(enumType);
    }

    @Override
    public E convertToEntityAttribute(String dbColumn) {
        return EnumCodeConverterUtils.getEnumFromCode(targetEnumClass, dbColumn);
    }
}
