package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.ScheduleSeatId;
import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.dto.MovieAndSchedulesDto;
import com.dbd.seoulcinema.dto.ViewSchedulesFormDto;
import com.dbd.seoulcinema.repository.MovieRepository;
import com.dbd.seoulcinema.repository.ScheduleRepository;
import com.dbd.seoulcinema.repository.ScheduleSeatRepository;
import com.dbd.seoulcinema.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleSeatRepository scheduleSeatRepository;
    private final SeatRepository seatRepository;

    public List<MovieAndSchedulesDto> getMovieAndSchedules(LocalDate screeningDate){
        return scheduleRepository.findMovieAndSchedules(screeningDate);
    }

    public List<MovieAndSchedulesDto> getMovieSchedules(Long movieNumber, LocalDate screeningDate){
        return scheduleRepository.findMovieSchedules(movieNumber, screeningDate);
    }

    public List<ViewSchedulesFormDto> getSchedulesForm(List<MovieAndSchedulesDto> movieAndSchedulesDtos){
        List<ViewSchedulesFormDto> schedulesFormDto = new ArrayList<>();
        for (MovieAndSchedulesDto dto : movieAndSchedulesDtos) {
            List<ScheduleSeat> scheduleSeats = scheduleSeatRepository.findByScheduleNumber(dto.getScheduleNumber());
            Long remainingSeat = dto.getSeatQuantity() - scheduleSeats.size();
            ViewSchedulesFormDto viewDto = ViewSchedulesFormDto.builder()
                    .movieNumber(dto.getMovieNumber())
                    .movieName(dto.getMovieName())
                    .scheduleNumber(dto.getScheduleNumber())
                    .screeningStartTime(dto.getScreeningStartTime())
                    .screeningEndTime(dto.getScreeningEndTime())
                    .screeningDate(dto.getScreeningDate())
                    .theaterNumber(dto.getTheaterNumber())
                    .theaterFloor(dto.getTheaterFloor())
                    .seatQuantity(dto.getSeatQuantity())
                    .remainingSeat(remainingSeat)
                    .build();
            schedulesFormDto.add(viewDto);
        }
        return schedulesFormDto;
    }
}
