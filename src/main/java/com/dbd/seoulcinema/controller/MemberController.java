package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.dto.CreateMemberDto;
import com.dbd.seoulcinema.global.exception.DuplicateIdException;
import com.dbd.seoulcinema.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import static com.dbd.seoulcinema.global.exception.ErrorCode.DUPLICATE_ID_ERROR;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/members/dup/{id}") // 아이디 중복 확인
    public String checkDuplicateId(@PathVariable String id, Model model){
        Boolean tf = memberService.checkDuplicateId(id);
        if(tf){
            model.addAttribute("success", "true");
        }
        else{
            model.addAttribute("success", "false");
            model.addAttribute("exception", new DuplicateIdException(DUPLICATE_ID_ERROR));
        }
        return "signup";
    }

    @PostMapping("/api/members")
    public RedirectView createMember(@ModelAttribute("signup") CreateMemberDto createMemberDto, Model model){
        memberService.createMember(createMemberDto);
        model.addAttribute("success", "true");
        return new RedirectView("/login");
    }
}
