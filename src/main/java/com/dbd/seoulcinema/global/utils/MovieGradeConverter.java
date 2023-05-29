package com.dbd.seoulcinema.global.utils;

import com.dbd.seoulcinema.domain.enumeration.MovieGrade;

import javax.persistence.Converter;

@Converter
public class MovieGradeConverter extends AbstractEnumAttributeConverter<MovieGrade> {

    public MovieGradeConverter() {
        super(MovieGrade.class,"영화등급코드");
    }
}
