package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.dto.ViewSchedulesFormDto;
import com.dbd.seoulcinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping("/api/schedule-seat")
    public String processScheduleSeat(HttpSession session,
                                      @RequestParam("selectedSeats") List<String> selectedSeats){

        List<ScheduleSeat> selectedSeatList = new ArrayList<>();

        String scheduleNumber = (String) session.getAttribute("scheduleNumber");
        String theaterNumber = (String) session.getAttribute("theaterNumber");

        //selectedSeat 저장 후 id 리스트에 삽입
        //스케쥴넘버, 싯넘버 필요
        for (String selectedSeat : selectedSeats) {
            ScheduleSeat scheduleSeat = seatService.saveSelectedSeat(selectedSeat, scheduleNumber, theaterNumber);
            selectedSeatList.add(scheduleSeat);
        }

        session.setAttribute("selectedSeats", selectedSeatList);

        return "redirect:/api/payments/radio";
        /*
        @GetMapping("/payments/radio")로 보내기
        상영일정좌석 id랑 스케쥴넘버 넘기면 됨.
         */
    }
}
