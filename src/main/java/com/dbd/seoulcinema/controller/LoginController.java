package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.dto.LoginDto;
import com.dbd.seoulcinema.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @GetMapping(value = "/api/auth/login")
    public ModelAndView loginCheck(@ModelAttribute LoginDto loginDto, HttpSession session){
        String userId = loginService.login(loginDto, session);
        ModelAndView mav = new ModelAndView();

        if(userId != null){ // 로그인 성공
             mav.setViewName("index");
        }
        else{
            mav.setViewName("login");
            mav.addObject("message", "error");
        }
        return mav;
    }

    @GetMapping("/api/auth/logout")
    public ModelAndView logout(ModelAndView mav, HttpSession session){
        loginService.logout(session);
        mav.setViewName("index");
        mav.addObject("message", "logout");
        return mav;
    }

}
