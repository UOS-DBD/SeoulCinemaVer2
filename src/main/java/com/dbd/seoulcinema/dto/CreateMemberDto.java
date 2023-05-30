package com.dbd.seoulcinema.dto;

import lombok.Data;

@Data
public class CreateMemberDto {
    private String clientId;
    private String password;
    private String phoneNumber;
    private String birth;
}
