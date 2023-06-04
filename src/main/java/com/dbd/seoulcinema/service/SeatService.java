package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Seat;
import com.dbd.seoulcinema.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    public String makeSeatFormat(Seat seat){
        return seat.getRowNumber()+"-"+seat.getColNumber();
    }

}
