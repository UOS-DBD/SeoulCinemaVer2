package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.dto.MovieAndSchedulesDto;
import com.dbd.seoulcinema.dto.ViewSchedulesFormDto;
import com.dbd.seoulcinema.service.MovieService;
import com.dbd.seoulcinema.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        //TODO: 이 페이지에서 영화 장르, 등급 띄워주기
        return "schedule";
    }

    @GetMapping("/viewSchedulesForm")
    public String selectMovieAndScheduleForm(Model model, @ModelAttribute("schedulesForm")List<ViewSchedulesFormDto> viewSchedulesFormDtos){
        model.addAttribute("viewSchedulesForms", viewSchedulesFormDtos);
        return "viewSchedulesForm";
    }

    @PostMapping("/api/schedules")
    public String processSchedulesForm(@RequestParam(required = false) Long movieNumber,
                                       @RequestParam(required = false) String date,
                                       RedirectAttributes redirectAttributes){

        //폼에서 String 으로 받은거 LocalDate 로 가공
        LocalDate screeningDate = LocalDate.parse(date);

        if(movieNumber == null){ // 입력한 날짜에 상영하는 영화들과 일정 보여주기

            List<MovieAndSchedulesDto> movieAndSchedules = scheduleService.getMovieAndSchedules(screeningDate);
            List<ViewSchedulesFormDto> schedulesForm = scheduleService.getSchedulesForm(movieAndSchedules);
            //schedulesForm: 영화이름 + 몇관 몇층 + 스케쥴 + 총 좌석 + 잔여좌석 + 영화번호,스케쥴번호

            redirectAttributes.addFlashAttribute("schedulesForm", schedulesForm);

        }else{ //입력한 영화와 날짜에 상영하는 일정 보여주기
            List<MovieAndSchedulesDto> movieSchedules = scheduleService.getMovieSchedules(movieNumber, screeningDate);
            List<ViewSchedulesFormDto> schedulesForm = scheduleService.getSchedulesForm(movieSchedules);
            redirectAttributes.addFlashAttribute("schedulesForm", schedulesForm);
        }
        return "redirect:/viewSchedulesForm";
    }

}
