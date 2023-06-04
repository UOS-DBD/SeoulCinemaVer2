package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping("/api/schedule-seat")
    public String processScheduleSeat(@RequestParam("selectedSeats") List<String> selectedSeats){
        System.out.println(selectedSeats.size());
        for (String seat : selectedSeats) {
            System.out.println("Selected seat: " + seat);
        }
        return "redirect:/home";
    }
}
