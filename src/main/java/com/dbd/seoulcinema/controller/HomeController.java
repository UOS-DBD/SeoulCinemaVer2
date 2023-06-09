package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import com.dbd.seoulcinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MovieService movieService;
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
    @GetMapping(value ={ "/home", "/"})
    public String home(Model model, HttpSession session){
        List<Movie> movieList = movieService.getAllMovies();

        int len = movieList.size();

        List<Movie> movies = new ArrayList<>();

        //가장 마지막에 등록된 영화 (최신영화) 4개를 선별
        for(int i=len-1; i>len-5; i--){
            movies.add(movieList.get(i));
        }
        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("movies", movies);
        return "home";
    }

    @GetMapping("/admin/home")
    public String adminHome(Model model){
        List<Movie> movieList = movieService.getAllMovies();

        int len = movieList.size();

        List<Movie> movies = new ArrayList<>();

        //가장 마지막에 등록된 영화 (최신영화) 4개를 선별
        for(int i=len-1; i>len-5; i--){
            movies.add(movieList.get(i));
        }
        model.addAttribute("movies", movies);
        return "admin/adminhome";
    }

}
