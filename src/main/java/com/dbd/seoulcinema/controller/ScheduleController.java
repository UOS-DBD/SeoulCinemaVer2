package com.dbd.seoulcinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class ScheduleController {
    @GetMapping("/schedules")
    public String getAllSchedules(@RequestParam Long movieNumber,
                                  @RequestParam String screeningDate){

    }

}
