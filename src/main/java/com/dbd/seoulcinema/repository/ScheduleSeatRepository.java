package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.ScheduleSeatId;
import com.dbd.seoulcinema.domain.entity.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, ScheduleSeatId> {
    List<ScheduleSeat> findByScheduleNumber(String scheduleNumber);
}
