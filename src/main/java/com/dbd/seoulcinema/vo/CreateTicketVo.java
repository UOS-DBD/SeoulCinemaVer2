package com.dbd.seoulcinema.vo;

import lombok.Data;

@Data
public class CreateTicketVo {

    private String cardNumber1;

    private String cardNumber2;

    private String cardNumber3;

    private String cardNumber4;

    private String discountType;

    private Integer point;

    private String accountNumber;

    private String bankName;
}
