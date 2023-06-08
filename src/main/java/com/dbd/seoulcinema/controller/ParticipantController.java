package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.entity.Participant;
import com.dbd.seoulcinema.dto.CreateParticipantDto;
import com.dbd.seoulcinema.dto.ViewParticipantListDto;
import com.dbd.seoulcinema.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping("/admin/participants/create-form")
    public String viewParticipantCreateForm(){


        return "admin/viewParticipantCreateForm";
    }

    @GetMapping("/admin/participants/{participantNumber}")
    public String viewParticipantPutForm(@PathVariable("participantNumber") Long participantNumber, Model model){

        model.addAttribute("participantNumber", participantNumber);
        return "admin/viewParticipantPutForm";
    }
    @GetMapping("/admin/participants")
    public String viewParticipantList(Model model){

        List<ViewParticipantListDto> participants = participantService.viewParticipantList();
        model.addAttribute("participants",participants);
        return "admin/viewParticipantList";
    }

    @PostMapping("/admin/participants")
    public String createParticipant(@ModelAttribute CreateParticipantDto createParticipantDto){

        participantService.createParticipant(createParticipantDto);
        return "redirect:/api/admin/participants";
    }

    @PostMapping("/admin/participants/{participantNumber}")
    public String putParticipant(@PathVariable("participantNumber") Long participantNumber,
                                 @ModelAttribute CreateParticipantDto createParticipantDto){

        participantService.putParticipant(participantNumber,createParticipantDto);

        return "redirect:/api/admin/participants";
    }

    @PostMapping("/admin/participants-delete/{participantNumber}")
    public String deleteParticipant(@PathVariable("participantNumber") Long participantNumber){

        participantService.deleteParticipant(participantNumber);
        return "redirect:/api/admin/participants";
    }
}
