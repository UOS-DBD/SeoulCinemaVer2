package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.dto.LoginDto;
import com.dbd.seoulcinema.global.exception.DuplicateIdException;
import com.dbd.seoulcinema.global.exception.LoginFailedException;
import com.dbd.seoulcinema.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.dbd.seoulcinema.global.exception.ErrorCode.DUPLICATE_ID_ERROR;
import static com.dbd.seoulcinema.global.exception.ErrorCode.LOGIN_FAILED;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("formData", new LoginDto());
        return "login";
    }

    @PostMapping(value = "/api/auth/login")
    public RedirectView loginCheck(@ModelAttribute("formData") @Valid LoginDto loginDto, HttpSession session, Model model){
        System.out.println("this is logincheck");
        String userId = loginService.login(loginDto, session);

        if(userId != null){ // 로그인 성공
            System.out.println("login success");
            model.addAttribute("success", "true");
            //return "redirect:home";
            return new RedirectView("/home");
        }
        else{
            System.out.println("login failed");
            model.addAttribute("success", "false");
            model.addAttribute("exception", new LoginFailedException(LOGIN_FAILED));
            return new RedirectView("/login");
        }
    }

    @PostMapping("/api/auth/logout")
    public String logout(Model model, HttpSession session){
        loginService.logout(session);
        model.addAttribute("success", "true");
        return "home";
    }

}
