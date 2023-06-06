package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.entity.Participant;
import com.dbd.seoulcinema.domain.entity.ParticipantMovie;
import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import com.dbd.seoulcinema.dto.CreateMovieAndParticipantDto;
import com.dbd.seoulcinema.dto.CreateMovieDto;
import com.dbd.seoulcinema.dto.CreateParticipantDto;
import com.dbd.seoulcinema.dto.MovieDetailDto;
import com.dbd.seoulcinema.global.utils.MovieGenreConverter;
import com.dbd.seoulcinema.global.utils.MovieGradeConverter;
import com.dbd.seoulcinema.repository.MovieRepository;
import com.dbd.seoulcinema.repository.ParticipantMovieRepositorty;
import com.dbd.seoulcinema.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantMovieRepositorty participantMovieRepositorty;
    @Transactional
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Transactional
    public List<MovieDetailDto> getMovieDetail(Long movieNumber) {
        List<MovieDetailDto> movieDetail = movieRepository.findMovieDetail(movieNumber);
        if(movieDetail.isEmpty()){
            return null;
        }
        else{
            System.out.println(movieDetail.get(0).getMovieGrade().toString());
            return movieDetail;
        }
    }

    @Transactional
    public void craeteMovie(CreateMovieAndParticipantDto createMovieAndParticipantDto) {
        Movie movie = Movie.builder()
                .movieName(createMovieAndParticipantDto.getCreateMovieDto().getMovieName())
                .runningTime(createMovieAndParticipantDto.getCreateMovieDto().getRunningTime())
                .movieGenre(MovieGenre.valueOf(createMovieAndParticipantDto.getCreateMovieDto().getMovieGenre()))
                .movieGrade(MovieGrade.valueOf(createMovieAndParticipantDto.getCreateMovieDto().getMovieGrade()))
                .movieIntroduction(createMovieAndParticipantDto.getCreateMovieDto().getMovieIntroduction())
                .movieImage("") // 이미지 어떻게 넣을지
                .screeningStatus(ScreeningStatus.valueOf(createMovieAndParticipantDto.getCreateMovieDto().getScreeningStatus())).build();
        movieRepository.save(movie);
        movieRepository.flush();

        List<CreateParticipantDto> createParticipantDtoList = createMovieAndParticipantDto.getCreateParticipantDto();
        for(int i = 0 ; i < createParticipantDtoList.size() ; i++){
            Participant participant = Participant.builder()
                    .participantType(ParticipantType.valueOf(createParticipantDtoList.get(i).getParticipantType()))
                    .participantName(createParticipantDtoList.get(i).getParticipantName())
                    .build();
            participantRepository.save(participant);
            participantRepository.flush();

            participantMovieRepositorty.save(ParticipantMovie.builder()
                    .movieNumber(movie)
                    .participantNumber(participant).build());
            participantMovieRepositorty.flush();
        }
    }

}
