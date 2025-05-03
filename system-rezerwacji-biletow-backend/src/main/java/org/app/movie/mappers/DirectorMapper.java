package org.app.movie.mappers;

import org.app.movie.Director;
import org.app.movie.dto.DirectorDto;

public interface DirectorMapper {
    static Director mapToDirector(DirectorDto dto){
        return Director.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    static DirectorDto mapToDirectorDto(Director director){
        return DirectorDto.builder()
                .id(director.getId())
                .name(director.getName())
                .surname(director.getSurname())
                .email(director.getEmail())
                .phone(director.getPhone())
                .build();
    }
}
