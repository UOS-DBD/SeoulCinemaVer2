package com.dbd.seoulcinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {


    @GetMapping("/payments/radio")
    public String viewPaymentRadio(HttpSession httpSession, Model model){

        String clientId= "mim501";
        String scheduleNumber = "20230601011";
        List<Long> seatNumber = new ArrayList<>(Arrays.asList(2L,3L));

        httpSession.setAttribute("clientId","mim501");
        httpSession.setAttribute("scheduleNumber","20230601011");
        httpSession.setAttribute("seatNumber",seatNumber);

        return "paymentRadio";
    }
}
