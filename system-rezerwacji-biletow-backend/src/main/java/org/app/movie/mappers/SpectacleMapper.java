package org.app.movie.mappers;

import org.app.config.DateTimeFormatUtil;
import org.app.movie.Spectacle;
import org.app.movie.dto.SpectacleDto;
import org.app.room.mapper.RoomMapper;

public interface SpectacleMapper {
    static Spectacle mapToSpectacle(SpectacleDto spectacleDto){
        return Spectacle.builder()
                .id(spectacleDto.getId())
                .date(DateTimeFormatUtil.parseDate(spectacleDto.getDate()))
                .room(RoomMapper.mapToRoom(spectacleDto.getRoom()))
                .movie(MovieMapper.mapToMovie(spectacleDto.getMovie()))
                .build();
    }

    static SpectacleDto mapToSpectacleDto(Spectacle spectacle){
        return SpectacleDto.builder()
                .id(spectacle.getId())
                .date(DateTimeFormatUtil.parseToString(spectacle.getDate()))
                .room(RoomMapper.mapToRoomDto(spectacle.getRoom()))
                .movie(MovieMapper.mapToMovieDto(spectacle.getMovie()))
                .build();
    }
}
