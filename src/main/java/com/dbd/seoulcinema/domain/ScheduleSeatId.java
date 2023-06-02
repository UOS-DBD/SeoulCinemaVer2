package com.dbd.seoulcinema.domain;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ScheduleSeatId implements Serializable {

    @Column(name = "SEAT_NUMBER")
    private Long seatNumber;

    @Column(name="SCHEDULE_NUMBER")
    private String scheduleNumber;

    @Override
    public int hashCode() {
        return Objects.hash(scheduleNumber,seatNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ScheduleSeatId id = (ScheduleSeatId) obj;
        return Objects.equals(this.seatNumber, id.getSeatNumber()) &&
            Objects.equals(this.scheduleNumber, id.getScheduleNumber());
    }
}
