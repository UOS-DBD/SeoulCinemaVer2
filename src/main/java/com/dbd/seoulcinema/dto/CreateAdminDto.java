package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.entity.Admin;
import com.dbd.seoulcinema.domain.entity.Member;
import com.dbd.seoulcinema.domain.enumeration.ClientGrade;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAdminDto {
    private String adminId;
    private String password;

    @Builder
    public Admin toEntity(CreateAdminDto createAdminDto){
        return Admin.builder()
                .adminId(createAdminDto.getAdminId())
                .password(createAdminDto.getPassword())
                .build();
    }
}
