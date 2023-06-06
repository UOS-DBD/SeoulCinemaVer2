package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.dto.CreateAdminDto;
import com.dbd.seoulcinema.dto.CreateMemberDto;
import com.dbd.seoulcinema.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
