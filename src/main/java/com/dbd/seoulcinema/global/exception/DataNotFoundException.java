package com.dbd.seoulcinema.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class    DataNotFoundException extends CustomException {

    public DataNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

  /*  public DataNotFoundException(HttpStatus httpStatus, String desc){

        super(super.getErrorCode().getStatus()=httpStatus,)
    }
  errorCode 를 동적으로 생성하는 방법을 추후에 찾아봐야할 것 같습니다. > 현재 예외처리 프로세스가 확장성 및 유지 보수성에 좋나?(팀원들과 의논)
  */
}
