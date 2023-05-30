package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Member;
import com.dbd.seoulcinema.dto.CreateMemberDto;
import com.dbd.seoulcinema.global.exception.DuplicateIdException;
import com.dbd.seoulcinema.global.exception.ErrorCode;
import com.dbd.seoulcinema.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Boolean checkDuplicateId(String id) {
        Optional<Member> userId = memberRepository.findById(id);

        if(userId.isEmpty()) { // 사용 불가능한 아이디인 경우
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    @Transactional
    public void createMember(CreateMemberDto createMemberDto) {
        String birth = createMemberDto.getBirth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(birth, formatter);
        memberRepository.save(createMemberDto.toEntitiy(createMemberDto, localDate));

    }
}
