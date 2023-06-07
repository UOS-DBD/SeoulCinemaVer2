package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.domain.entity.Theater;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import com.dbd.seoulcinema.dto.CreateAdminDto;
import com.dbd.seoulcinema.dto.MovieAndSchedulesDto;
import com.dbd.seoulcinema.dto.ScreeningTimeDto;
import com.dbd.seoulcinema.service.AdminService;
import com.dbd.seoulcinema.service.MovieService;
import com.dbd.seoulcinema.service.ScheduleService;
import com.dbd.seoulcinema.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final MovieService movieService;
    private final TheaterService theaterService;
    private final ScheduleService scheduleService;

    @PostMapping("/api/admin")
    public RedirectView createMember(@ModelAttribute("signup") CreateAdminDto createAdminDto, Model model){
        adminService.createAdmin(createAdminDto);
        model.addAttribute("success", "true");
        return new RedirectView("/admin/login");
    }

    @GetMapping("/admin/movie-management")
    public String viewMovieManagementForm(){
        return "admin/adminMovieManagement";
    }

    @GetMapping("admin/movie/add")
    public String viewMovieAddForm(){
        return "admin/adminMovieManagement";
    }
    @PostMapping

    @GetMapping("/admin/participant-management")
    public String viewParticipantManagementForm(){
        return "admin/adminParticipantManagement";
    }

    //스케쥴 관리 시작
    @GetMapping("/admin/schedule-management") //상영관리 등록,수정,삭제 페이지로
    public String viewScheduleManagementForm(){
        return "admin/adminScheduleManagement";
    }
    @GetMapping("/admin/schedule/create")
    public String viewScheduleCreateForm(Model model){ //상영관리 등록 페이지로
        List<Movie> movieList = movieService.getOnScreenMovies(ScreeningStatus.Y);
        List<Theater> theaterList = theaterService.getAllTheaters();

        model.addAttribute("movies", movieList);
        model.addAttribute("theaters", theaterList);
        model.addAttribute("screeningStartTime", "");
        model.addAttribute("screeningDate", "");
        model.addAttribute("screeningSession", "");
        return "admin/adminScheduleCreate";
    }
    @PostMapping("/api/admin/schedule/create")
    public String processScheduleCreate(@RequestParam Long movieNumber,
                                        @RequestParam String theaterNumber,
                                        @RequestParam String screeningDate,
                                        @RequestParam String screeningStartTime,
                                        @RequestParam String screeningSession){
        //TODO: 스케쥴 만들어서 저장
        String runningTime = movieService.getMovie(movieNumber).getRunningTime();
        ScreeningTimeDto screeningTimeDto = adminService.makeScheduleDto(screeningDate, runningTime, screeningStartTime, screeningSession, theaterNumber);
        adminService.createSchedule(movieNumber, theaterNumber, screeningTimeDto);

        return "redirect:/admin/home";
    }

    @GetMapping("admin/schedule/delete")
    public String viewScheduleDeleteForm(Model model){
        List<MovieAndSchedulesDto> allMovieSchedules = scheduleService.getAllMovieSchedules();
        model.addAttribute("schedules", allMovieSchedules);
        return "admin/adminScheduleDelete";
    }
    @PostMapping("/api/admin/schedule/delete/{scheduleNumber}")
    public String processScheduleDelete(@PathVariable String scheduleNumber){
        scheduleService.deleteSchedule(scheduleNumber);
        return "redirect:/admin/home";
    }



    //스케쥴 관리 끝

    @GetMapping("/admin/discount-management")
    public String viewDiscountManagementForm(){
        return "admin/adminDiscountManagement";
    }
}
