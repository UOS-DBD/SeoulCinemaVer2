package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, String> {
}
