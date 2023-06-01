package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
