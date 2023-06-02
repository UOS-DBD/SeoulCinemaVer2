package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import com.dbd.seoulcinema.domain.enumeration.PaymentType;
import com.dbd.seoulcinema.vo.CreateTicketFinalVo;
import com.dbd.seoulcinema.vo.CreateTicketVo;
import com.dbd.seoulcinema.global.constants.Constants;
import com.dbd.seoulcinema.service.MemberService;
import com.dbd.seoulcinema.service.TicketService;
import com.dbd.seoulcinema.vo.ScheduleSeatVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.EnumSet;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final MemberService memberService;


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

        String userId = (String) httpSession.getAttribute("clientId");
        Long memberPoint = memberService.getMemberPoint("mim501"); // mim501을 userId로 추후 변경해야함!
        model.addAttribute(Constants.MEMBER_POINT, 10000);

        if (paymentType.equals("CARD")) {
            httpSession.setAttribute(Constants.PAYMENT_TYPE, PaymentType.CARD);
            return "cardTicket";
        } else if (paymentType.equals("ACCOUNT")) {
            httpSession.setAttribute(Constants.PAYMENT_TYPE, PaymentType.ACCOUNT);
            return "accountTicket";
        } else {
            httpSession.setAttribute(Constants.PAYMENT_TYPE, PaymentType.POINT);
            return "pointTicket";
        }

    }

    @PostMapping("/tickets")
    public String makeTickets(HttpSession httpSession) {

        CreateTicketFinalVo createTicketFinalVo = (CreateTicketFinalVo) httpSession.getAttribute("createTicketFinalVo");
        System.out.println("scheduleNumber~!~!~!~!~!~!11111"+createTicketFinalVo.getScheduleNumber());
        String memberId = (String) httpSession.getAttribute("clientId");
        PaymentType paymentType = (PaymentType) httpSession.getAttribute(Constants.PAYMENT_TYPE);
        System.out.println("scheduleNumber~!~!~!~!~!~!"+createTicketFinalVo.getScheduleNumber());
        String result = ticketService.makeTicketsAndPayment(createTicketFinalVo, memberId,paymentType);

        return "home";
    }

    @PostMapping("/tickets/final-check")
    public String makeTicketsFinalCheck(@ModelAttribute CreateTicketVo createTicketVo, HttpSession httpSession, Model model) {

        Optional<DiscountType> findDiscountType = EnumSet.allOf(DiscountType.class)
                .stream()
                .filter(f -> f.toString().equals(createTicketVo.getDiscountType()))
                .findAny();

        ScheduleSeatVo scheduleSeatVo = (ScheduleSeatVo) httpSession.getAttribute("scheduleSeatVo");

        Integer totalPrice = ticketService.calculateTotalPrice(scheduleSeatVo.getSeats().size(), findDiscountType.get(), createTicketVo.getPoint());

        CreateTicketFinalVo createTicketFinalVo = new CreateTicketFinalVo(createTicketVo,
                scheduleSeatVo.getSeats().size(), totalPrice, findDiscountType.get(),scheduleSeatVo);

        model.addAttribute("totalPrice", totalPrice);
        httpSession.setAttribute("createTicketFinalVo", createTicketFinalVo);

        return "paymentFinal";
    }
}
