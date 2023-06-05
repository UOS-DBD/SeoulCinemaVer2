package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.domain.entity.Seat;
import com.dbd.seoulcinema.domain.enumeration.PaymentStatus;
import com.dbd.seoulcinema.repository.ScheduleRepository;
import com.dbd.seoulcinema.repository.ScheduleSeatRepository;
import com.dbd.seoulcinema.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleSeatRepository scheduleSeatRepository;

    public String makeSeatFormat(Seat seat){
        return seat.getRowNumber()+"-"+seat.getColNumber();
    }

    public ScheduleSeat saveSelectedSeat(String selectedSeat, String scheduleNumber){
        String[] split = selectedSeat.split("-");
        Seat seat = seatRepository.findByRowNumberAndColNumber(split[0], split[1]);
        Schedule schedule = scheduleRepository.findById(scheduleNumber).get();

        ScheduleSeat scheduleSeat = ScheduleSeat.builder()
                .seatNumber(seat)
                .paymentStatus(PaymentStatus.N)
                .scheduleNumber(schedule)
                .ticket(null)
                .build();
        return scheduleSeatRepository.save(scheduleSeat);
    }

}
