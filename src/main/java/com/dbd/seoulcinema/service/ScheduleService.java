package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.ScheduleSeatId;
import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import com.dbd.seoulcinema.domain.entity.Seat;
import com.dbd.seoulcinema.domain.enumeration.PaymentStatus;
import com.dbd.seoulcinema.dto.MovieAndSchedulesDto;
import com.dbd.seoulcinema.dto.ViewSchedulesFormDto;
import com.dbd.seoulcinema.repository.ScheduleRepository;
import com.dbd.seoulcinema.repository.ScheduleSeatRepository;
import com.dbd.seoulcinema.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleSeatRepository scheduleSeatRepository;
    private final SeatRepository seatRepository;

    public MovieAndSchedulesDto getSchedule(String scheduleNumber){
        return scheduleRepository.findMovieAndSchedule(scheduleNumber);
    }
    public List<MovieAndSchedulesDto> getMovieAndSchedules(LocalDate screeningDate){
        return scheduleRepository.findMovieAndSchedules(screeningDate);
    }

    public List<MovieAndSchedulesDto> getMovieSchedules(Long movieNumber, LocalDate screeningDate){
        return scheduleRepository.findMovieSchedules(movieNumber, screeningDate);
    }

    public List<MovieAndSchedulesDto> getAllMovieSchedules(){
        return scheduleRepository.findAllMovieSchedules();
    }

    @Transactional
    public boolean deleteSchedule(String scheduleNumber){
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleNumber);

        if (schedule.isEmpty()){
            return false;
        }
        else{
            List<ScheduleSeat> scheduleSeats = scheduleSeatRepository.findByScheduleNumber(schedule.get());
            if(scheduleSeats.isEmpty()){
                scheduleRepository.deleteById(scheduleNumber);
                return true;
            }
            else{
                return false;
            }
        }
    }
    public Schedule getScheduleEntity(String scheduleNumber){
        return scheduleRepository.findById(scheduleNumber).get();
    }

    public ViewSchedulesFormDto getScheduleForm(MovieAndSchedulesDto movieAndSchedulesDto){
        List<ScheduleSeat> scheduleSeats = scheduleSeatRepository.findByScheduleNumber(scheduleRepository.findById(movieAndSchedulesDto.getScheduleNumber()).get());
        return ViewSchedulesFormDto.builder()
                .movieNumber(movieAndSchedulesDto.getMovieNumber())
                .movieName(movieAndSchedulesDto.getMovieName())
                .scheduleNumber(movieAndSchedulesDto.getScheduleNumber())
                .screeningStartTime(movieAndSchedulesDto.getScreeningStartTime())
                .screeningEndTime(movieAndSchedulesDto.getScreeningEndTime())
                .screeningDate(movieAndSchedulesDto.getScreeningDate())
                .theaterNumber(movieAndSchedulesDto.getTheaterNumber())
                .theaterFloor(movieAndSchedulesDto.getTheaterFloor())
                .seatQuantity(movieAndSchedulesDto.getSeatQuantity())
                .remainingSeat(movieAndSchedulesDto.getSeatQuantity() - scheduleSeats.size())
                .build();
    }
    public List<ViewSchedulesFormDto> getSchedulesForm(List<MovieAndSchedulesDto> movieAndSchedulesDtos){
        List<ViewSchedulesFormDto> schedulesFormDto = new ArrayList<>();
        for (MovieAndSchedulesDto dto : movieAndSchedulesDtos) {
            List<ScheduleSeat> scheduleSeats = scheduleSeatRepository.findByScheduleNumber(scheduleRepository.findById(dto.getScheduleNumber()).get());
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

    public List<Seat> getSeats(String theaterNumber){
        return seatRepository.findTheaterNumber(theaterNumber);
    }

    public boolean isSeatBooked(Long seatNumber, String scheduleNumber) {
        return scheduleSeatRepository.existsById(new ScheduleSeatId(seatNumber, scheduleNumber));
    }

    @Transactional
    @Scheduled(cron = "0 * * ? * *")
    public void deleteScheduleSeatLock(){
        List<ScheduleSeat> scheduleSeats = scheduleSeatRepository.findAllByPaymentStatus(PaymentStatus.NO);
        for (ScheduleSeat scheduleSeat : scheduleSeats) {
            LocalDateTime createdDate = scheduleSeat.getCreatedDate();
            Duration elapsed = Duration.between(createdDate, LocalDateTime.now());
            Duration fiveMinutes = Duration.ofMinutes(5);
            if (elapsed.compareTo(fiveMinutes) >= 0) {
                scheduleSeatRepository.delete(scheduleSeat);
            }
        }
    }

}
