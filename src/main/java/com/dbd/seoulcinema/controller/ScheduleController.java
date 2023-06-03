package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.service.MovieService;
import com.dbd.seoulcinema.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final MovieService movieService;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;


    @GetMapping("/schedules")
    public String showSchedulesForm(Model model){
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("date", "");
        return "schedule";
    }

    @PostMapping("/api/schedules")
    public String processSchedulesForm(@RequestParam(required = false) Long movieNumber,
                                  @RequestParam(value = "date", required = false) String screeningDate){

        LocalDate date = LocalDate.parse(screeningDate);
        if(movieNumber == null){
            // 이 날에 상영하는 영화를 보여줘
            List<Object[]> movieAndSchedule = scheduleService.getMovieAndSchedule(date);
            //TODO : movie, schedule List 만들어서 model에 추가해서 보내 + 상영일정좌석 어케하지? + LocalDate와 오라클 DATE 비교


        }else{ //둘다 널이 아닐 시에
            System.out.println(date);
            List<Object[]> movieSchedules = scheduleService.getMovieSchedules(movieNumber, date);
        }

        return "schedule";
    }

}
