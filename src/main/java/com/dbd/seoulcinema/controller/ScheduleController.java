package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.dto.MovieAndSchedulesDto;
import com.dbd.seoulcinema.service.MovieService;
import com.dbd.seoulcinema.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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

    @GetMapping("/selectMovieAndSchedules")
    public String selectmovieAndScheduleForm(Model model){
        return "selectMovieSchedules";
    }
    @GetMapping("/selectMovieSchedules")
    public String selectmoiveSchedulesForm(Model model){
        return "selectMovieSchedules";
    }

    @PostMapping("/api/schedules")
    public String processSchedulesForm(@RequestParam(required = false) Long movieNumber,
                                       @RequestParam(required = false) String date,
                                       RedirectAttributes redirectAttributes){

        //폼에서 String으로 받은거 LocalDate로 가공
        LocalDate screeningDate = LocalDate.parse(date);

        if(movieNumber == null){
            // 이 날에 상영하는 영화를 보여줘
            List<MovieAndSchedulesDto> movieAndSchedules = scheduleService.getMovieAndSchedules(screeningDate);
            for (MovieAndSchedulesDto movieAndSchedule : movieAndSchedules) {
                System.out.println(movieAndSchedule.getMovieName());
                System.out.println(movieAndSchedule.getScreeningDate());
            }
            //TODO : movie, schedule List 만들어서 model에 추가해서 보내 + 상영일정좌석 어케하지? + LocalDate와 오라클 DATE 비교
            redirectAttributes.addFlashAttribute("movieAndSchedules", movieAndSchedules);
            return "redirect:/selectMovieAndSchedules";

        }else{ //둘다 널이 아닐 시에
            System.out.println(date);
            List<Schedule> movieSchedules = scheduleService.getMovieSchedules(movieNumber, screeningDate);
            for (Schedule movieSchedule : movieSchedules) {
                System.out.println(movieSchedule.getMovie().getMovieName());
                System.out.println(movieSchedule.getScreeningDate());
                System.out.println(movieSchedule.getScreeningStartTime());
            }
            redirectAttributes.addFlashAttribute("movieSchedules", movieSchedules);
            return "redirect:/selectMovieSchedules";
        }
    }

}
