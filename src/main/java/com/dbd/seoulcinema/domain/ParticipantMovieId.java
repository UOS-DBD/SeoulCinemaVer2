package com.dbd.seoulcinema.domain;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ParticipantMovieId implements Serializable {

    private Long participantNumber;
    private Long movieNumber;

    @Override
    public int hashCode() {
        return Objects.hash(participantNumber, movieNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ParticipantMovieId id = (ParticipantMovieId) obj;
        return Objects.equals(this.participantNumber, id.getParticipantNumber()) &&
            Objects.equals(this.movieNumber, id.getMovieNumber());
    }
}
