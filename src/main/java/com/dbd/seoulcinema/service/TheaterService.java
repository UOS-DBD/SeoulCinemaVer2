package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Theater;
import com.dbd.seoulcinema.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public List<Theater> getAllTheaters(){
        return theaterRepository.findAll();
    }
    public Theater getTheater(String theaterNumber){
        return theaterRepository.findById(theaterNumber).get();
    }


}
