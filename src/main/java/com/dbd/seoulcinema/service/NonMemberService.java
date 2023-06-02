package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.NonMember;
import com.dbd.seoulcinema.repository.NonmemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class NonMemberService {
    private final NonmemberRepository nonmemberRepository;

    @Transactional
    public void createNonmember(String phoneNumber, HttpSession session) {
        System.out.println("service start");
        nonmemberRepository.save(NonMember.builder()
                .phoneNumber(phoneNumber)
                .build());
        session.setAttribute("userId", phoneNumber);
        System.out.println("service end");
    }
}