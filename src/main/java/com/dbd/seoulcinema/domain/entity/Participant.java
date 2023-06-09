package com.dbd.seoulcinema.domain.entity;

import javax.persistence.*;

import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import com.dbd.seoulcinema.dto.CreateParticipantDto;
import com.dbd.seoulcinema.global.utils.ParticipantTypeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARTICIPANT" //, indexes = {
    //@Index(name = "idx_participant_pk", columnList = "participantNUmber ASC")
//}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long participantNumber;

    @Column(length = 16, nullable = false)
    private String participantName;


    @Convert(converter = ParticipantTypeConverter.class)
    @Column(columnDefinition = "CHAR(2)", nullable = false)
    private ParticipantType participantType;

    public void update(CreateParticipantDto dto) {
        this.participantName = dto.getParticipantName();
        this.participantType = dto.getParticipantType();
    }
}
