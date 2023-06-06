package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.domain.entity.Movie;
import com.dbd.seoulcinema.domain.entity.Participant;
import com.dbd.seoulcinema.domain.entity.ParticipantMovie;
import com.dbd.seoulcinema.domain.enumeration.MovieGenre;
import com.dbd.seoulcinema.domain.enumeration.MovieGrade;
import com.dbd.seoulcinema.domain.enumeration.ParticipantType;
import com.dbd.seoulcinema.domain.enumeration.ScreeningStatus;
import com.dbd.seoulcinema.dto.CreateMovieDto;
import com.dbd.seoulcinema.dto.CreateParticipantDto;
import com.dbd.seoulcinema.dto.MovieDetailDto;
import com.dbd.seoulcinema.repository.MovieRepository;
import com.dbd.seoulcinema.repository.ParticipantMovieRepositorty;
import com.dbd.seoulcinema.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
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
    public void craeteMovie(CreateMovieDto createMovieDto, List<CreateParticipantDto> createParticipantDtoList) {
        Optional<MovieGenre> findMovieGenre = EnumSet.allOf(MovieGenre.class)
                .stream()
                .filter(f -> f.toString().equals(createMovieDto.getMovieGenre()))
                .findAny();
        System.out.println("service"+ findMovieGenre.get().name());

        Optional<MovieGrade> findMovieGrade = EnumSet.allOf(MovieGrade.class)
                .stream()
                .filter(f -> f.toString().equals(createMovieDto.getMovieGrade()))
                .findAny();


        Movie movie = Movie.builder()
                .movieName(createMovieDto.getMovieName())
                .runningTime(createMovieDto.getRunningTime())
                .movieGenre(MovieGenre.valueOf(MovieGenre.class, createMovieDto.getMovieGenre()))
                .movieGrade(findMovieGrade.get())
                .movieIntroduction(createMovieDto.getMovieIntroduction())
                .movieImage("") // 이미지 어떻게 넣을지
                .screeningStatus(ScreeningStatus.valueOf(createMovieDto.getScreeningStatus())).build();
        movieRepository.save(movie);
        movieRepository.flush();


        for(int i = 0 ; i < createParticipantDtoList.size() ; i++){
            CreateParticipantDto createParticipantDto = createParticipantDtoList.get(i);
            Optional<ParticipantType> findParticipantType = EnumSet.allOf(ParticipantType.class)
                    .stream()
                    .filter(f -> f.toString().equals(createParticipantDto.getParticipantType()))
                    .findAny();

            Participant participant = Participant.builder()
                    .participantType(findParticipantType.get())
                    .participantName(createParticipantDto.getParticipantName())
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
