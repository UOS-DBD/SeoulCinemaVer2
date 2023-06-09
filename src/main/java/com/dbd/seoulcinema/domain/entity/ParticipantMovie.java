package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.ParticipantMovieId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARTICIPANT_MOVIE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@IdClass(ParticipantMovieId.class)
public class ParticipantMovie {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARTICIPANT_NUMBER")
    private Participant participantNumber;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_NUMBER")
    private Movie movieNumber;
}
