package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.service.MovieService;
import com.dbd.seoulcinema.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final MovieService movieService;


    @GetMapping("/schedules")
    public String showSchedulesForm(Model model){
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("date", new Date());
        return "schedule";
    }

    @PostMapping("/api/schedules")
    public String processSchedulesForm(@RequestParam(required = false) Long movieNumber,
                                  @RequestParam(required = false) LocalDate screeningDate){

        if(movieNumber == null){
            // 이 날에 상영하는 영화를 보여줘

        }else{ //둘다 널이 아닐 시에

        }

        return "schedule";
    }

}
