package com.dbd.seoulcinema.vo;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateTicketFinalVo {

    private String cardNumber1;

    private String cardNumber2;

    private String cardNumber3;

    private String cardNumber4;

    private DiscountType discountType;

    private Integer point;

    private String accountNumber;

    private String bankName;

    private Integer standardPrice;

    private Integer totalPrice;

    String scheduleNumber;

    List<Long> seats;

    public CreateTicketFinalVo(CreateTicketVo vo, Integer seatNumber, Integer totalPrice, DiscountType discountType
            , ScheduleSeatVo scheduleSeatVo) {
        this.cardNumber1 = vo.getCardNumber1();
        this.cardNumber2 = vo.getCardNumber2();
        this.cardNumber3 = vo.getCardNumber3();
        this.cardNumber4 = vo.getCardNumber4();
        this.discountType = discountType;
        this.point = vo.getPoint();
        this.accountNumber = vo.getAccountNumber();
        this.bankName = vo.getBankName();
        this.standardPrice = 12000 * seatNumber;
        this.totalPrice = totalPrice;
        this.scheduleNumber = scheduleSeatVo.getScheduleNumber();
        this.seats = scheduleSeatVo.getSeats();
    }
}
