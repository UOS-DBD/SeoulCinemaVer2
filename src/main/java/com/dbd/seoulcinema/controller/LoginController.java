package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.dto.LoginDto;
import com.dbd.seoulcinema.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @PostMapping(value = "/api/auth/login")
    public String loginCheck(@ModelAttribute LoginDto loginDto, HttpSession session, Model model){
        String userId = loginService.login(loginDto, session);

        if(userId != null){ // 로그인 성공
            model.addAttribute("success", "true");
            return "index";
        }
        else{
            model.addAttribute("success", "false");
            return "login";
        }
    }

    @PostMapping("/api/auth/logout")
    public String logout(Model model, HttpSession session){
        loginService.logout(session);
        model.addAttribute("success", "true");
        return "index";
    }

}
