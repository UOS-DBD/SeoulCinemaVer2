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
import com.dbd.seoulcinema.repository.MovieRepository;
import com.dbd.seoulcinema.repository.ParticipantMovieRepositorty;
import com.dbd.seoulcinema.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
            return movieDetail;
        }
    }

    @Transactional
    public void craeteMovie(MultipartFile image, CreateMovieAndParticipantDto createMovieAndParticipantDto)  {
        String fileName = image.getOriginalFilename();
        Path imagePath = Path.of("src/main/resources/static/img/" + fileName); // 이미지를 저장할 경로

        try {
            // 이미지 파일을 지정된 경로로 복사합니다.
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 복사 실패 처리
        }

        CreateMovieDto createMovieDto = createMovieAndParticipantDto.getCreateMovieDto();
        List<CreateParticipantDto> createParticipantDtoList = createMovieAndParticipantDto.getCreateParticipantDto();

        Movie movie = Movie.builder()
                .movieName(createMovieDto.getMovieName())
                .runningTime(createMovieDto.getRunningTime())
                .movieGenre(createMovieDto.getMovieGenre())
                .movieGrade(createMovieDto.getMovieGrade())
                .movieIntroduction(createMovieDto.getMovieIntroduction())
                .movieImage(createMovieDto.getMovieImage()) // 이미지 어떻게 넣을지
                .screeningStatus(createMovieDto.getScreeningStatus())
                .build();
        movieRepository.save(movie);
        movieRepository.flush();


        for(int i = 0 ; i < createParticipantDtoList.size() ; i++){
            CreateParticipantDto createParticipantDto = createParticipantDtoList.get(i);

            Participant participant = Participant.builder()
                    .participantType(createParticipantDto.getParticipantType())
                    .participantName(createParticipantDto.getParticipantName())
                    .build();
            participantRepository.save(participant);
            participantRepository.flush();

            participantMovieRepositorty.save(ParticipantMovie.builder()
                    .movieNumber(movie)
                    .participantNumber(participant).build());
            participantMovieRepositorty.flush();
        }
        System.out.println("service end");
    }
}
