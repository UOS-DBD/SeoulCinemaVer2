package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Member;
import com.dbd.seoulcinema.dto.LoginDto;
import com.dbd.seoulcinema.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    @Transactional
    public String login(LoginDto loginDto, HttpSession session){
        Optional<Member> member = memberRepository.findById(loginDto.getId());

        // 아이디 존재 x
        if(member.isEmpty()){
            return null;
        }
        else{
            // 로그인 성공
            if(member.get().getPassword().equals(loginDto.getPassword())){
                session.setAttribute("userId", loginDto.getId());
                return loginDto.getId();
            }
            else{ // 비밀번호 틀림
                return null;
            }
        }

    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
