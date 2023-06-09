package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.ParticipantMovieId;
import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.entity.Participant;
import com.dbd.seoulcinema.domain.entity.ParticipantMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantMovieRepositorty extends JpaRepository<ParticipantMovie, ParticipantMovieId> {
    @Modifying
    @Query("delete from ParticipantMovie pm where pm.movieNumber = :movie")
    void deleteByMovieNumber(@Param("movie") Movie movie);

    List<ParticipantMovie> findByParticipantNumber(Participant participant);
}
