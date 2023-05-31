package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.NonMember;
import com.dbd.seoulcinema.dto.CreateMemberDto;
import com.dbd.seoulcinema.dto.LoginDto;
import com.dbd.seoulcinema.service.NonMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class NonmemberController {

    private final NonMemberService nonMemberService;

    @RequestMapping(value = "/nonmember/login", method = RequestMethod.GET)
    public String nonmemberLogin(Model model){
        model.addAttribute("phoneNumber", "");
        return "nonmemberlogin";
    }

    @PostMapping("/api/nonmembers")
    public RedirectView createNonmember(@ModelAttribute("phoneNumber") String phoneNumber, Model model){
        System.out.println("controller start");
        nonMemberService.createNonmember(phoneNumber);
        model.addAttribute("success", true);
        System.out.println("controller end");
        return new RedirectView("/home");// 예약 페이지 리다이렉트 수정 필요
    }
}
