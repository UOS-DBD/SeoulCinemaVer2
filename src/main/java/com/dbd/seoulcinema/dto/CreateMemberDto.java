package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.entity.Member;
import com.dbd.seoulcinema.domain.enumeration.ClientGrade;
import com.dbd.seoulcinema.global.utils.ClientGradeConverter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMemberDto {
    private String clientId;
    private String password;
    private String phoneNumber;
    private String birth;

    @Builder
    public Member toEntitiy(CreateMemberDto createMemberDto, LocalDate birth){
        return Member.builder()
                .clientId(createMemberDto.getClientId())
                .password(createMemberDto.getPassword())
                .phoneNumber(createMemberDto.getPhoneNumber())
                .point(0L)
                .clientGrade(ClientGrade.NORMAL)
                .localDate(birth)
                .build();
    }
}
