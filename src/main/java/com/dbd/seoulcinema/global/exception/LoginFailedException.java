package com.dbd.seoulcinema.global.exception;

import lombok.Getter;

@Getter
public class LoginFailedException extends CustomException{
    public LoginFailedException(ErrorCode errorCode) { super(errorCode);}
}
