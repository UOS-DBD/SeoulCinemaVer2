package com.dbd.seoulcinema.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TARGET_ENUM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 코드에 맞는 enum 데이터가 없습니다."),
    TARGET_CODE_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 enum에 맞는 코드 데이터가 없습니다."),
    DUPLICATE_ID_ERROR(HttpStatus.CONFLICT, "중복된 ID입니다."),
    LOGIN_FAILED(HttpStatus.CONFLICT, "로그인에 실패했습니다.");

    private HttpStatus status;
    private String desc;

    ErrorCode(HttpStatus status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
