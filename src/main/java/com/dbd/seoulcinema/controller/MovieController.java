package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.dto.*;
import com.dbd.seoulcinema.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

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
        return "admin/adminmovie";
    }

    @GetMapping(value = "/admin/movie/detail")
    public String adminMovieDetail(Model model, @RequestParam(value = "movieNumber", required = true) Long movieNumber){
        List<MovieDetailDto> movieDetail = movieService.getMovieDetail(movieNumber);
        model.addAttribute("movie", movieDetail);
        return "admin/adminmoviedetail";
    }

    @GetMapping(value = "/admin/movie/create")
    public String adminCreateMoviePage(Model model){ // 페이지 랜더링
//        model.addAttribute("movie", new CreateMovieDto());
//        List<CreateParticipantDto> createParticipantDtoList = new ArrayList<>();
//        model.addAttribute("participant", createParticipantDtoList);
        return "admin/adminmoviecreate";
    }

    @PostMapping(value = "/api/admin/movie/create")
    public String adminCreateMovie(Model model, @RequestParam("image") MultipartFile image, @RequestParam("createMovieAndParticipantDto") String createMovieAndParticipantDto) {
        CreateMovieAndParticipantDto dto = null;
        try {
            dto = objectMapper.readValue(createMovieAndParticipantDto, CreateMovieAndParticipantDto.class);
            // createMovieAndParticipantDto를 처리하는 로직을 구현합니다.
            // ...
        } catch (JsonProcessingException e) {
            // JSON 파싱 오류 처리
            e.printStackTrace();
        }System.out.println("CONTROLLER START");
        movieService.craeteMovie(image, dto);
        System.out.println("CONTROLLER END");
        model.addAttribute("success", "true");
        System.out.println("REDIRECTING");
        return "admin/adminmovie";
    }

    @DeleteMapping(value = "/api/admin/movie/delete")
    public String adminDeleteMovie(Model model, @RequestBody DeleteMovieDto deleteMovieDto){
        Long movieNumber = Long.parseLong(deleteMovieDto.getMovieNumber());

        if(movieService.deleteMovie(movieNumber)){
            model.addAttribute("success", "true");
            return "admin/adminmovie";
        }
        else{
            model.addAttribute("success", "false");
            return "/admin/movie/detail?movieNumber="+movieNumber;
        }
    }


}
