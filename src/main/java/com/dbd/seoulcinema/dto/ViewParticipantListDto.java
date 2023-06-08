package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewParticipantListDto {

    private Long participantNumber;

    private String participantName;

    private ParticipantType participantType;
}
