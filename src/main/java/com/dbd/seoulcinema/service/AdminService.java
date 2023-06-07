package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Schedule;
import com.dbd.seoulcinema.dto.CreateAdminDto;
import com.dbd.seoulcinema.dto.ScreeningTimeDto;
import com.dbd.seoulcinema.repository.AdminRepository;
import com.dbd.seoulcinema.repository.MovieRepository;
import com.dbd.seoulcinema.repository.ScheduleRepository;
import com.dbd.seoulcinema.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;
    private final TheaterRepository theaterRepository;


    @Transactional
    public void createAdmin(CreateAdminDto createAdminDto) {
        adminRepository.save(createAdminDto.toEntity(createAdminDto));
    }

    public ScreeningTimeDto makeScheduleDto(String screeningDate, String runningTime, String screeningStartTime, String screeningSession, String theaterNumber){
        String[] temp1 = screeningDate.split("-");
        int year = Integer.parseInt(temp1[0]);
        int month = Integer.parseInt(temp1[1]);
        int day = Integer.parseInt(temp1[2]);
        String[] temp2 = screeningStartTime.split(":");
        int hour = Integer.parseInt(temp2[0]);
        int min = Integer.parseInt(temp2[1]);

        String[] temp3 = runningTime.split(":");
        long runningHour = Long.parseLong(temp3[0]);
        long runningMin = Long.parseLong(temp3[1]);

        LocalDate date = LocalDate.parse(screeningDate, DateTimeFormatter.ISO_DATE);

        LocalDateTime startTime = LocalDateTime.of(year, month, day, hour, min);
        LocalDateTime startTimePlusHour = startTime.plusHours(runningHour);
        LocalDateTime screeningEndTime = startTimePlusHour.plusMinutes(runningMin);

        String scheduleNumber =temp1[0]+temp1[1]+temp1[2]+theaterNumber+screeningSession;

        return new ScreeningTimeDto(date, startTime, screeningEndTime, scheduleNumber, Integer.parseInt(screeningSession));
    }

    public void createSchedule(Long movieNumber, String theaterNumber, ScreeningTimeDto dto){
        Schedule schedule = Schedule.builder()
                .scheduleNumber(dto.getScheduleNumber())
                .screeningDate(dto.getScreeningDate())
                .screeningStartTime(dto.getScreeningStartTime())
                .screeningEndTime(dto.getScreeningEndTime())
                .screeningSession(dto.getScreeningSession())
                .movie(movieRepository.findById(movieNumber).get())
                .theater(theaterRepository.findById(theaterNumber).get())
                .build();
        scheduleRepository.save(schedule);
    }
}
