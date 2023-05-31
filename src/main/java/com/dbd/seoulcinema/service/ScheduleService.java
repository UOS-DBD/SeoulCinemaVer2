package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.repository.MovieRepository;
import com.dbd.seoulcinema.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;

}
