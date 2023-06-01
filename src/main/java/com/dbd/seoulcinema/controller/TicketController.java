package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Ticket;
import com.dbd.seoulcinema.dto.ViewTicketsListDto;
import com.dbd.seoulcinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

 /*   @GetMapping("/tickets")
    public String viewTicketList(@RequestParam("paymentType") String paymentType) {

        //Object userId = httpSession.getAttribute("userId");

        Optional<Ticket> findTickets = ticketService.viewTicketList();

        // model.addAttribute("tickets", findTickets);
        System.out.println("!!!!!!!!!!!!" + paymentType);
        return "index";
    } */

    @GetMapping("/tickets/radio")
    public String getTicketRadio(@RequestParam("paymentType") String paymentType, HttpSession httpSession, Model model) {

        if (paymentType.equals("CARD")) {
            return "cardTicket";
        } else if (paymentType.equals("ACCOUNT")) {
            return "accountTicket";
        } else {
            return "pointTicket";
        }

    }
}
