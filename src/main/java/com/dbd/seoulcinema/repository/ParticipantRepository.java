package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Participant findByParticipantName(String participantName);
}
