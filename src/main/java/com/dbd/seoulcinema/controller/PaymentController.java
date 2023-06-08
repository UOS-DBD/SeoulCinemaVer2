package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.dto.ViewPaymentListDto;
import com.dbd.seoulcinema.dto.ViewSpecificPaymentDto;
import com.dbd.seoulcinema.global.constants.Constants;
import com.dbd.seoulcinema.service.PaymentService;
import com.dbd.seoulcinema.vo.ScheduleSeatVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {


    private final PaymentService paymentService;

    //결제 타입을 고를 떄 호출하는 api
    @GetMapping("/payments/radio")
    public String viewPaymentRadio(HttpSession httpSession, Model model) {
        List<ScheduleSeat> selectedSeats = (List<ScheduleSeat>) httpSession.getAttribute(
                "selectedSeats");
        model.addAttribute(Constants.STANDARD_PRICE, 12000 * selectedSeats.size()); // 할인전 표준 금액 렌더링
        List<Long> seats = selectedSeats.stream().map(s -> s.getSeatNumber().getSeatNumber())
                .collect(Collectors.toList());
        ScheduleSeatVo scheduleSeatVo = new ScheduleSeatVo(
                selectedSeats.get(0).getScheduleNumber().getScheduleNumber(), seats);

        httpSession.setAttribute("scheduleSeatVo", scheduleSeatVo);

        boolean loggedIn = (httpSession.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);

        return "paymentRadio";
    }

    @GetMapping("/payments")
    public String viewPaymentsList(HttpSession httpSession, Model model) {

        String clientId = (String) httpSession.getAttribute(Constants.USER_ID_SESSION);
        List<ViewPaymentListDto> payments = paymentService.viewpaymentList(clientId);

        boolean loggedIn = (httpSession.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);

        model.addAttribute("payments", payments);

        return "viewPaymentList";
    }


    @GetMapping("/payments/detail/{paymentNumber}")
    public String viewSpecificPayment(@PathVariable("paymentNumber") String paymentNumber, Model model, HttpSession session) {

        ViewSpecificPaymentDto dto = paymentService.viewSpecificPayment(paymentNumber);
        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("paymentInfo", dto);
        model.addAttribute("discounts", dto.getDiscounts());
        if (dto.getBankName() == null) {
            return "viewSpecificPaymentCard";
        }else{
            return "viewSpecificPaymentAccount";
        }
    }
}
