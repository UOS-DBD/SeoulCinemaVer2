package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.dto.MovieDetailDto;
import com.dbd.seoulcinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Transactional
    public List<MovieDetailDto> getMovieDetail(Long movieNumber) {
        List<MovieDetailDto> movieDetail = movieRepository.findMovieDetail(movieNumber);
        if(movieDetail.isEmpty()){
            return null;
        }
        else{
            System.out.println(movieDetail.get(0).getMovieGrade().toString());
            return movieDetail;
        }
    }
}
