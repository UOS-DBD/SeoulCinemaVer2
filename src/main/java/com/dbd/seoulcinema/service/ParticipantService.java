package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Participant;
import com.dbd.seoulcinema.domain.entity.ParticipantMovie;
import com.dbd.seoulcinema.dto.CreateParticipantDto;
import com.dbd.seoulcinema.dto.ViewParticipantListDto;
import com.dbd.seoulcinema.repository.ParticipantMovieRepositorty;
import com.dbd.seoulcinema.dto.CreateParticipantDto;
import com.dbd.seoulcinema.dto.ViewParticipantListDto;
import com.dbd.seoulcinema.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantService {


    private final ParticipantRepository participantRepository;
    private final ParticipantMovieRepositorty participantMovieRepository;

    @Transactional
    public void createParticipant(CreateParticipantDto dto){

        participantRepository.save(
                Participant.builder()
                        .participantName(dto.getParticipantName())
                        .participantType(dto.getParticipantType())
                        .build());
    }


    @Transactional(readOnly = true)
    public List<ViewParticipantListDto> viewParticipantList(){

        List<ViewParticipantListDto> participants = participantRepository.findAll().stream()
                .map(s -> {
                    return new ViewParticipantListDto(s.getParticipantNumber(), s.getParticipantName(), s.getParticipantType());
                })
                .collect(Collectors.toList());

        return participants;
    }

    @Transactional
    public void putParticipant(Long participantNumber, CreateParticipantDto dto){

        Participant participant = participantRepository.findById(participantNumber).get();
        participant.update(dto);

    }

    @Transactional
    public boolean deleteParticipant(Long participantNumber){
        Participant participant = participantRepository.findById(participantNumber).get();
        List<ParticipantMovie> participantMovies = participantMovieRepository.findByParticipantNumber(participant);
        if(participantMovies.isEmpty()){ // 삭제 불가
            participantRepository.delete(participant);
            return true;
        }
        else{
            return false;
        }
    }
}
