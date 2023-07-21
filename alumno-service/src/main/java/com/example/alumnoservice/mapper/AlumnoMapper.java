package com.example.alumnoservice.mapper;

import com.example.alumnoservice.dto.AlumnoDto;
import com.example.alumnoservice.entity.Alumno;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlumnoMapper {
    AlumnoMapper MAPPER = Mappers.getMapper(AlumnoMapper.class);

    AlumnoDto mappToDto(Alumno alumno);

    Alumno mappToEntity(AlumnoDto alumnoDto);
}
