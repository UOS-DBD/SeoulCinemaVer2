package com.dbd.seoulcinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieAndParticipantDto {
    private CreateMovieDto createMovieDto;
    private List<CreateParticipantDto> createParticipantDto;
}
