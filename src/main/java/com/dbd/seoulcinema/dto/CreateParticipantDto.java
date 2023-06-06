package com.dbd.seoulcinema.dto;

import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipantDto {
    private String participantName;
    private String participantType; // enum
}
