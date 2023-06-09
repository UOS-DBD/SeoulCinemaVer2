package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import com.dbd.seoulcinema.dto.*;
import com.dbd.seoulcinema.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/movie")
    public String movieList(Model model, HttpSession session){
        List<Movie> movieList = movieService.getAllMovies();
        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("movies", movieList);
        return "movie";
    }

    @GetMapping(value = "/movie/detail")
    public String movieDetail(Model model, HttpSession session, @RequestParam(value = "movieNumber", required = true) Long movieNumber){
        List<MovieDetailDto> movieDetail = movieService.getMovieDetail(movieNumber);
        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);

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
    public String adminMovieDetail(@RequestParam(value = "movieNumber", required = true) Long movieNumber, Model model, HttpSession session) {
        boolean loggedIn = (session.getAttribute("adminId") != null);

        model.addAttribute("loggedIn", loggedIn);
        List<MovieDetailDto> movieDetail = movieService.getMovieDetail(movieNumber);
        model.addAttribute("movie", movieDetail);
        System.out.println("넘어가라 "+movieDetail.get(0).getMovieNumber());
        return "admin/adminmoviedetail";
    }

    @GetMapping(value = "/admin/movie/create")
    public String adminCreateMoviePage(Model model, HttpSession session){ // 페이지 랜더링
//        model.addAttribute("movie", new CreateMovieDto());
//        List<CreateParticipantDto> createParticipantDtoList = new ArrayList<>();
//        model.addAttribute("participant", createParticipantDtoList);
        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);
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
        }
        movieService.craeteMovie(image, dto);

        model.addAttribute("success", "true");
        System.out.println("REDIRECTING");
        return "admin/adminmovie";
    }

    @GetMapping(value = "/api/admin/movie/delete")
    public String adminDeleteMovie(Model model, @RequestParam(value = "movieNumber", required = true) Long movieNumber){

        if(movieService.deleteMovie(movieNumber)){
            model.addAttribute("success", "true");
            System.out.println("delete success");
            return "redirect:/admin/movie";
        }
        else{
            model.addAttribute("success", "false");
            System.out.println("delete failed");
            return "redirect:/admin/movie/detail?movieNumber="+movieNumber;
        }
    }

    @GetMapping(value = "/admin/movie/update")
    public String adminMovieUpdatePage(Model model, @RequestParam(value = "movieNumber", required = true) Long movieNumber, HttpSession session){
        boolean loggedIn = (session.getAttribute("adminId") != null);

        model.addAttribute("loggedIn", loggedIn);
        List<MovieDetailDto> movieDetail = movieService.getMovieDetail(movieNumber);
        System.out.println("길이2: "+movieDetail.size());
        List<MovieGenre> movieGenres = Arrays.asList(MovieGenre.values());
        List<MovieGrade> movieGrades = Arrays.asList(MovieGrade.values());
        List<ScreeningStatus> screeningStatuses = Arrays.asList(ScreeningStatus.values());
        List<ParticipantType> participantTypes = Arrays.asList(ParticipantType.values());

        model.addAttribute("movie", movieDetail);
        model.addAttribute("movieGenre", movieGenres);
        model.addAttribute("movieGrade", movieGrades);
        model.addAttribute("screeningStatus", screeningStatuses);
        model.addAttribute("participantType", participantTypes);
        return "admin/adminmovieupdate";
    }

    @PostMapping(value = "/api/admin/movie/update")
    public String adminMovieUpdate(Model model, @RequestParam("image") MultipartFile image,
                                   @RequestParam("createMovieAndParticipantDto") String createMovieAndParticipantDto,
                                   @RequestParam("movieNumber") Long movieNumber) {
        CreateMovieAndParticipantDto dto = null;
        try {
            dto = objectMapper.readValue(createMovieAndParticipantDto, CreateMovieAndParticipantDto.class);
            // createMovieAndParticipantDto를 처리하는 로직을 구현합니다.
            // …
        } catch (JsonProcessingException e) {
            // JSON 파싱 오류 처리
            e.printStackTrace();
        }
        if(movieService.updateMovie(image, dto, movieNumber)){
            model.addAttribute("success", "true");
        }
        return "redirect:/admin/movie/detail?movieNumber="+movieNumber;
    }
}
