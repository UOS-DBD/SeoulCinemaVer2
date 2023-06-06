package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.dto.CreateMovieAndParticipantDto;
import com.dbd.seoulcinema.dto.CreateMovieDto;
import com.dbd.seoulcinema.dto.CreateParticipantDto;
import com.dbd.seoulcinema.dto.MovieDetailDto;
import com.dbd.seoulcinema.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/movie")
    public String movieList(Model model){
        List<Movie> movieList = movieService.getAllMovies();

        model.addAttribute("movies", movieList);
        return "movie";
    }

    @GetMapping(value = "/movie/detail")
    public String movieDetail(Model model, @RequestParam(value = "movieNumber", required = true) Long movieNumber){
        System.out.println("moviedetail start");
        List<MovieDetailDto> movieDetail = movieService.getMovieDetail(movieNumber);
        System.out.println("moviedetail end");
        model.addAttribute("movie", movieDetail);
        return "moviedetail";
    }

    @GetMapping(value = "/admin/movie")
    public String adminMovieList(Model model){
        List<Movie> movieList = movieService.getAllMovies();

        model.addAttribute("movies", movieList);
        return "adminmovie";
    }

    @GetMapping(value = "/admin/movie/detail")
    public String adminMovieDetail(Model model, @RequestParam(value = "movieNumber", required = true) Long movieNumber){
        List<MovieDetailDto> movieDetail = movieService.getMovieDetail(movieNumber);
        model.addAttribute("movie", movieDetail);
        return "adminmoviedetail";
    }

    @GetMapping(value = "/admin/movie/create")
    public String adminCreateMoviePage(Model model){ // 페이지 랜더링
//        model.addAttribute("movie", new CreateMovieDto());
//        List<CreateParticipantDto> createParticipantDtoList = new ArrayList<>();
//        model.addAttribute("participant", createParticipantDtoList);
        return "adminmoviecreate";
    }

    @PostMapping(value = "/api/admin/movie/create")
    public String adminCreateMovie(Model model, @RequestBody CreateMovieAndParticipantDto createMovieAndParticipantDto) {
        CreateMovieDto createMovieDto = createMovieAndParticipantDto.getCreateMovieDto();
        List<CreateParticipantDto> createParticipantDtoList = createMovieAndParticipantDto.getCreateParticipantDto();
        System.out.println("controller"+ createMovieDto.getMovieGenre());
        movieService.craeteMovie(createMovieDto, createParticipantDtoList);
        model.addAttribute("success", "true");
        return "adminmovie";
    }


}
