package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.dto.CreateAdminDto;
import com.dbd.seoulcinema.dto.CreateMemberDto;
import com.dbd.seoulcinema.dto.LoginDto;
import com.dbd.seoulcinema.global.exception.LoginFailedException;
import com.dbd.seoulcinema.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.dbd.seoulcinema.global.exception.ErrorCode.LOGIN_FAILED;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session){
        model.addAttribute("formData", new LoginDto());
        model.addAttribute("signup", new CreateMemberDto());

        boolean loggedIn = (session.getAttribute("userId") != null);

        model.addAttribute("loggedIn", loggedIn);
        return "login";
    }

    @PostMapping(value = "/api/auth/login")
    public RedirectView loginCheck(@ModelAttribute("formData") @Valid LoginDto loginDto, HttpSession session, Model model){
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

    @GetMapping("/api/auth/logout")
    public String logout(Model model, HttpSession session){
        loginService.logout(session);
        model.addAttribute("success", "true");
        return "redirect:/home";
    }

    // admin
    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String adminLogin(Model model){
        model.addAttribute("formData", new LoginDto());
        model.addAttribute("signup", new CreateAdminDto());
        return "admin/adminlogin";
    }

    @PostMapping(value = "/api/admin/auth/login")
    public RedirectView adminLoginCheck(@ModelAttribute("formData") @Valid LoginDto loginDto, HttpSession session, Model model){

        String userId = loginService.adminLogin(loginDto, session);

        if(userId != null){ // 로그인 성공
            model.addAttribute("success", "true");
            // 페이지 생성 필요
            return new RedirectView("/admin/home");
        }
        else{
            model.addAttribute("success", "false");
            model.addAttribute("exception", new LoginFailedException(LOGIN_FAILED));
            return new RedirectView("/admin/login");
        }
    }
}
