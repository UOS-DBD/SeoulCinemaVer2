package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
