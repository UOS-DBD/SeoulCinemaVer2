package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

}
