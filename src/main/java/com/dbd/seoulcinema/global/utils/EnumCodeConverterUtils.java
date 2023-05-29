package com.dbd.seoulcinema.global.utils;

import com.dbd.seoulcinema.domain.enumeration.CommonDescCode;
import com.dbd.seoulcinema.global.exception.DataNotFoundException;
import com.dbd.seoulcinema.global.exception.ErrorCode;
import lombok.NoArgsConstructor;
import org.thymeleaf.util.StringUtils;

import java.util.EnumSet;


@NoArgsConstructor
public class EnumCodeConverterUtils {

    public static <T extends Enum<T> & CommonDescCode> T getEnumFromCode(Class<T> targetEnum, String code) {

        if (StringUtils.isEmptyOrWhitespace(code)) {
            return null;
        }

        return EnumSet.allOf(targetEnum)
                .stream()
                .filter(f -> f.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.TARGET_ENUM_NOT_FOUND));
    }


    public static <T extends Enum<T> & CommonDescCode> String getCodeFromEnum(T enumType) {
        if(enumType == null){
            return "";
        }

        return enumType.getCode();
    }
}
