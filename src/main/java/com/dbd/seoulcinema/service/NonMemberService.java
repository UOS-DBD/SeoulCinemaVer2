package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.NonMember;
import com.dbd.seoulcinema.repository.NonmemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

    @Transactional
    @Scheduled(fixedRate = 3600000) // 1시간
    public void deleteNonmember(){
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        LocalDateTime nowDay = nowLocalDateTime.truncatedTo(ChronoUnit.DAYS);

        List<NonMember> nonMemberList = nonmemberRepository.findAll();
        for(int i = 0; i < nonMemberList.size() ; i++){
            LocalDateTime nonMemberLocalDateTime = nonMemberList.get(i).getCreatedDate();
            LocalDateTime nonMemberDay = nonMemberLocalDateTime.truncatedTo(ChronoUnit.DAYS);

            int compareDay = nowDay.compareTo(nonMemberDay);

            if(compareDay > 7){
                nonmemberRepository.delete(nonMemberList.get(i));
            }
        }
    }
}
