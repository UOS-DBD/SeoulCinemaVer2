package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
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
    @GetMapping("/")
    public String home(Model model){
        List<Movie> movieList = movieRepository.findAll();

        int len = movieList.size();
        Map<String, Object> movieMap = new HashMap<>();
        List<Movie> movie = new ArrayList<>();

        //가장 마지막에 등록된 영화 (최신영화) 4개를 선별
        for(int i=len-1; i<len-5; i--){
            movie.add(movieList.get(i));
        }
        movieMap.put("movies", movie);
        model.addAttribute("movies", movieMap);
        return "home";
    }

}
