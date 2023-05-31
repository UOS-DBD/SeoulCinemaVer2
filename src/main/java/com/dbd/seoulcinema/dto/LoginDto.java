package com.dbd.seoulcinema.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @NotBlank
    private String id;

    @NotNull
    private String password;
}
