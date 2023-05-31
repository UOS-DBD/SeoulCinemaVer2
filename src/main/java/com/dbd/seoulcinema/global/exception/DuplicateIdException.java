package com.dbd.seoulcinema.global.exception;

import lombok.Getter;

@Getter
public class DuplicateIdException extends CustomException {
    public DuplicateIdException(ErrorCode errorCode) {
        super(errorCode);
    }
}
