package com.dbd.seoulcinema.domain.entity;

import javax.persistence.*;

import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import com.dbd.seoulcinema.global.utils.ParticipantTypeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARTICIPANT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PARTICIPANT_NUMBER")
    private Long ParticipantNumber;

    @Column(name = "PARTICIPANT_NAME", length = 16)
    private String participantName;

    @Column(name = "PARTICIPANT_CODE")
    @Convert(converter = ParticipantTypeConverter.class)
    private ParticipantType participantType;
}
