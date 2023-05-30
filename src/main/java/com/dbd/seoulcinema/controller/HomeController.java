package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MovieRepository movieRepository;
/*
    {
        "movieNumber": 3
        "movieName": "범죄도시3",
        "runningTime": "02:08",
        "genre": "스릴러",
        "grade": "15세 이상 시청가",
        "movieImage": "http://~~~"
    }
 */
    @GetMapping("/home")
    public String home(Model model){
        List<Movie> movieList = movieRepository.findAll();

        int len = movieList.size();

        List<Movie> movies = new ArrayList<>();

        //가장 마지막에 등록된 영화 (최신영화) 4개를 선별
        for(int i=len-1; i>len-5; i--){
            movies.add(movieList.get(i));
        }

        model.addAttribute("movies", movies);
        return "home";
    }

}
