package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import com.dbd.seoulcinema.domain.enumeration.PaymentType;
import com.dbd.seoulcinema.dto.ViewSpecificTicketDto;
import com.dbd.seoulcinema.dto.ViewTicketsListDto;
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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final MemberService memberService;


    @GetMapping("/tickets")
    public String viewTicketList(HttpSession httpSession, Model model) {

        //String clientId = (String) httpSession.getAttribute(Constants.USER_ID_SESSION);

        List<ViewTicketsListDto> tickets = ticketService.viewTicketList("mim501"); // 추후 clientId로 변경해야함
        model.addAttribute("tickets", tickets);


        return "viewTicketList";
    }

    @GetMapping("/tickets/{ticketNumber}")
    public String viewSpecificTicket(@PathVariable("ticketNumber") String ticketNumber, HttpSession httpSession, Model model) {

        ViewSpecificTicketDto dto = ticketService.viewSpecificTicket(ticketNumber);
        httpSession.setAttribute("ticketNumber", ticketNumber); //예매 취소할때 사용될 티켓 넘버 세션에 저장
        model.addAttribute("ticketInfo", dto);

        return "viewSpecificTicket";
    }

    @GetMapping("/tickets/radio")
    public String getTicketRadio(@RequestParam("paymentType") String paymentType, HttpSession httpSession, Model model) {

        String userId = (String) httpSession.getAttribute(Constants.USER_ID_SESSION);
        Long memberPoint = memberService.getMemberPoint(userId);
        model.addAttribute(Constants.MEMBER_POINT,memberPoint);

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

    //최종 결제 버튼을 누를 때 호출하는 api >> 결제 완료하면 메인 화면으로 이동
    @PostMapping("/tickets")
    public String makeTickets(HttpSession httpSession) {

        CreateTicketFinalVo createTicketFinalVo = (CreateTicketFinalVo) httpSession.getAttribute("createTicketFinalVo");

        String clientId = (String) httpSession.getAttribute(Constants.USER_ID_SESSION);
        PaymentType paymentType = (PaymentType) httpSession.getAttribute(Constants.PAYMENT_TYPE);

        String result = ticketService.makeTicketsAndPayment(createTicketFinalVo, clientId,paymentType);

        //TODO 결제 할인 테이블 생성하는 로직 추가해야 함

        return "home";
    }

    //결제 정보를 입력한 후 최종 결제 금액을 보여줄때 호출하는 api
    @PostMapping("/tickets/final-check")
    public String makeTicketsFinalCheck(@ModelAttribute CreateTicketVo createTicketVo, HttpSession httpSession, Model model) {

        //radio 박스에서 선택한 정보(결제 타입)가 어떤 정보인지 판단하는 로직
        Optional<DiscountType> findDiscountType = EnumSet.allOf(DiscountType.class)
                .stream()
                .filter(f -> f.toString().equals(createTicketVo.getDiscountType()))
                .findAny();

        ScheduleSeatVo scheduleSeatVo = (ScheduleSeatVo) httpSession.getAttribute("scheduleSeatVo");

        Integer totalPrice = ticketService.calculateTotalPrice(scheduleSeatVo.getSeats().size(),
                findDiscountType.get(), createTicketVo.getPoint());

        //결제에 필요한 최종 정보를 저장하는 VO 생성
        CreateTicketFinalVo createTicketFinalVo = new CreateTicketFinalVo(createTicketVo,
                scheduleSeatVo.getSeats().size(), totalPrice, findDiscountType.get(),scheduleSeatVo);

        model.addAttribute("totalPrice", totalPrice);
        httpSession.setAttribute("createTicketFinalVo", createTicketFinalVo);

        return "paymentFinal";
    }
}
