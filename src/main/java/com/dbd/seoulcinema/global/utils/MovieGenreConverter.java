package com.dbd.seoulcinema.global.utils;

import com.dbd.seoulcinema.domain.enumeration.MovieGenre;

import javax.persistence.Converter;

@Converter
public class MovieGenreConverter extends AbstractEnumAttributeConverter<MovieGenre> {

    public MovieGenreConverter() {
        super(MovieGenre.class,"영화장르코드");
    }
}
