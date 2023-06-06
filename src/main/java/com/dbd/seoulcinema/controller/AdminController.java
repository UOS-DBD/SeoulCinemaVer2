package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.dto.CreateAdminDto;
import com.dbd.seoulcinema.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/api/admin")
    public RedirectView createMember(@ModelAttribute("signup") CreateAdminDto createAdminDto, Model model){
        adminService.createAdmin(createAdminDto);
        model.addAttribute("success", "true");
        return new RedirectView("/admin/login");
    }

    @GetMapping("/admin/movie-management")
    public String viewMovieManagementForm(){
        return "admin/adminMovieManagement";
    }
    @GetMapping("/admin/participant-management")
    public String viewParticipantManagementForm(){
        return "admin/adminParticipantManagement";
    }
    @GetMapping("/admin/schedule-management")
    public String viewScheduleManagementForm(){
        return "admin/adminScheduleManagement";
    }
    @GetMapping("/admin/theater-management")
    public String viewTheaterManagementForm(){
        return "admin/adminTheaterManagement";
    }
    @GetMapping("/admin/discount-management")
    public String viewDiscountManagementForm(){
        return "admin/adminDiscountManagement";
    }
}
