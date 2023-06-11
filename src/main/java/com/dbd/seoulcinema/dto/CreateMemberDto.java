package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.entity.Member;
import com.dbd.seoulcinema.domain.enumeration.ClientGrade;
import com.dbd.seoulcinema.global.utils.ClientGradeConverter;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;

@Data
public class CreateMemberDto {
    private String clientId;
    private String password;
    private String phoneNumber;
    private String birth;

    @Builder
    public Member toEntity(CreateMemberDto createMemberDto, LocalDate birth){
        return Member.builder()
                .clientId(createMemberDto.getClientId())
                .password(BCrypt.hashpw(createMemberDto.getPassword(),BCrypt.gensalt()))
                .phoneNumber(createMemberDto.getPhoneNumber())
                .point(0L)
                .clientGrade(ClientGrade.NORMAL)
                .birth(birth)
                .build();
    }
}
