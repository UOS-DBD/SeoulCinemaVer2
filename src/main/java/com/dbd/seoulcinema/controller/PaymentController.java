package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.global.constants.Constants;
import com.dbd.seoulcinema.vo.ScheduleSeatVo;
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


    //결제 타입을 고를 떄 호출하는 api
    @GetMapping("/payments/radio")
    public String viewPaymentRadio(HttpSession httpSession, Model model) {


        String scheduleNumber = "20230601011";
        List<Long> seats = new ArrayList<>(Arrays.asList(9L, 10L)); // 좌석 선택하는 api 완료된 후 @ModelAttribute 사용해서 코드 수정할 것
        model.addAttribute(Constants.STANDARD_PRICE,12000*seats.size()); // 할인전 표준 금액 렌더링

        ScheduleSeatVo scheduleSeatVo = new ScheduleSeatVo(scheduleNumber, seats);
        httpSession.setAttribute(Constants.USER_ID_SESSION, "mim501");
        httpSession.setAttribute("scheduleSeatVo", scheduleSeatVo);

        return "paymentRadio";
    }
}
