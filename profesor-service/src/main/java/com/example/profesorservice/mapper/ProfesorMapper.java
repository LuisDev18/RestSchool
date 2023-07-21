package com.example.profesorservice.mapper;

import com.example.profesorservice.dto.ProfesorDto;
import com.example.profesorservice.entity.Profesor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfesorMapper {

    ProfesorMapper MAPPER = Mappers.getMapper(ProfesorMapper.class);

    ProfesorDto mappToDto(Profesor profesor);

    Profesor mappToEntity(ProfesorDto profesorDto);
}
