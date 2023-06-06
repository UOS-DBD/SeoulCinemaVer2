package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.dao.ViewSpecificPaymentDao;
import com.dbd.seoulcinema.domain.entity.Discount;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.domain.entity.Seat;
import com.dbd.seoulcinema.dto.ViewPaymentListDto;
import com.dbd.seoulcinema.dto.ViewSpecificPaymentDto;
import com.dbd.seoulcinema.repository.DiscountRepository;
import com.dbd.seoulcinema.repository.PaymentRepository;
import com.dbd.seoulcinema.repository.ScheduleSeatRepository;
import com.dbd.seoulcinema.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final SeatRepository seatRepository;

    private final DiscountRepository discountRepository;

    @Transactional
    public List<ViewPaymentListDto> viewpaymentList(String clientId){
       return paymentRepository.findPaymentsByMember(clientId);
    }

    @Transactional
    public ViewSpecificPaymentDto viewSpecificPayment(String paymentNumber){

        ViewSpecificPaymentDao dao = paymentRepository.findSpecificPaymentInfoById(paymentNumber);
        List<Seat> seats = seatRepository.findAllSeatsByTicketNumber(dao.getTicketNumber());
        String location = Seat.getSeatLocation(seats);
        List<Discount> discounts = discountRepository.findAllDiscountsByPaymentNumber(paymentNumber);

        return new ViewSpecificPaymentDto(dao,location,discounts);
    }
}
