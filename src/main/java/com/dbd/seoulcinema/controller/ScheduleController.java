package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.domain.entity.Seat;
import com.dbd.seoulcinema.dto.MovieAndSchedulesDto;
import com.dbd.seoulcinema.dto.ViewSchedulesFormDto;
import com.dbd.seoulcinema.service.MovieService;
import com.dbd.seoulcinema.service.ScheduleService;
import com.dbd.seoulcinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final MovieService movieService;
    private final SeatService seatService;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;


    @GetMapping("/schedules")
    public String showSchedulesForm(Model model, HttpSession session){
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("date", "");

        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);
        //TODO: 이 페이지에서 영화 장르, 등급 띄워주기
        return "schedule";
    }

    @GetMapping("/viewSchedulesForm")
    public String selectMovieAndScheduleForm(Model model, HttpSession session, @ModelAttribute("schedulesForm")List<ViewSchedulesFormDto> viewSchedulesFormDtos){
        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("viewSchedulesForms", viewSchedulesFormDtos);
        return "viewSchedulesForm";
    }

    @PostMapping("/api/schedules/{scheduleNumber}")
    public String showScheduleSeatsForm(Model model,
                                      HttpSession session,
                                      @PathVariable String scheduleNumber){
        // TODO: 쿼리 300개 해결

        MovieAndSchedulesDto movieAndSchedule = scheduleService.getSchedule(scheduleNumber);
        ViewSchedulesFormDto scheduleForm = scheduleService.getScheduleForm(movieAndSchedule);
        List<Seat> seats = scheduleService.getSeats(scheduleForm.getTheaterNumber());

        List<String> scheduleSeats = new ArrayList<>();

        for (Seat seat : seats) {
            boolean isBooked = scheduleService.isSeatBooked(seat.getSeatNumber(), scheduleForm.getScheduleNumber());
            if(isBooked){
                scheduleSeats.add(seatService.makeSeatFormat(seat));
            }
        }

        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("scheduleSeats", scheduleSeats);

        model.addAttribute("scheduleForm", scheduleForm);
        model.addAttribute("seats", seats);

        session.setAttribute("scheduleNumber", scheduleNumber);

        /*

         */
        List<String> rowList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O");
        model.addAttribute("rowList", rowList);
        return "viewScheduleSeatsForm";
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
