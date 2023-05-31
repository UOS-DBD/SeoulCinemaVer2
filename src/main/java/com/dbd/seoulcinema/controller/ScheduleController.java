package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedules")
    public String getAllSchedules(@RequestParam(required = false) Long movieNumber,
                                  @RequestParam(required = false) String screeningDate){

        if(movieNumber==null && screeningDate==null){
            //예외처리
        }else if(movieNumber == null && screeningDate != null){
            // 상영하는 영화를 보여줘

        }else if(movieNumber != null && screeningDate == null){
            // 영화의 상영일정들을 보여줘

        }else{ //둘다 널이 아닐 시에
            //

        }

        return "schedule";
    }

}
