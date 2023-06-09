package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Participant;
import com.dbd.seoulcinema.dto.CreateParticipantDto;
import com.dbd.seoulcinema.dto.ViewParticipantListDto;
import com.dbd.seoulcinema.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping("/admin/participants/create-form")
    public String viewParticipantCreateForm(Model model, HttpSession session){
        boolean loggedIn = (session.getAttribute("adminId") != null);

        model.addAttribute("loggedIn", loggedIn);
        return "admin/viewParticipantCreateForm";
    }

    @GetMapping("/admin/participants/{participantNumber}")
    public String viewParticipantPutForm(@PathVariable("participantNumber") Long participantNumber, Model model, HttpSession session){
        boolean loggedIn = (session.getAttribute("adminId") != null);

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("participantNumber", participantNumber);
        return "admin/viewParticipantPutForm";
    }

    @PostMapping("/admin/participants")
    public String createParticipant(@ModelAttribute CreateParticipantDto createParticipantDto, HttpSession session, Model model){
        boolean loggedIn = (session.getAttribute("adminId") != null);

        model.addAttribute( "loggedIn", loggedIn);
        participantService.createParticipant(createParticipantDto);
        return "redirect:/admin/participant-management";
    }

    @PostMapping("/admin/participants-put/{participantNumber}")
    public String putParticipant(@PathVariable("participantNumber") Long participantNumber,
                                 @ModelAttribute CreateParticipantDto createParticipantDto, HttpSession session, Model model){
        boolean loggedIn = (session.getAttribute("adminId") != null);

        model.addAttribute("loggedIn", loggedIn);
        participantService.putParticipant(participantNumber,createParticipantDto);

        return "redirect:/admin/participant-management";
    }

    @PostMapping("/admin/participants-delete/{participantNumber}")
    public String deleteParticipant(@PathVariable("participantNumber") Long participantNumber, HttpSession session, Model model, RedirectAttributes redirectAttributes){
        boolean loggedIn = (session.getAttribute("adminId") != null);

        model.addAttribute("loggedIn", loggedIn);
        // TODO: 외래키 참조 되어있으면 관계자 삭제 못하게 하기

        if(participantService.deleteParticipant(participantNumber)){
            redirectAttributes.addFlashAttribute("deleteSuccess", "true");
            return "redirect:/admin/participant-management";
        }
        else{
            redirectAttributes.addFlashAttribute("deleteSuccess", "false");
            return "redirect:/admin/participant-management";
        }
    }
}
