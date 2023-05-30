package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.dto.CreateMemberDto;
import com.dbd.seoulcinema.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/members/dup/{id}") // 아이디 중복 확인
    public String checkDuplicateId(@PathVariable String id){
        memberService.checkDuplicateId(id);

        return "signup";
    }

//    @PostMapping("/api/members")
//    public String createMember(@ModelAttribute CreateMemberDto createMemberDto){
//        memberService.createMember(createMemberDto);
//    }
}
