package com.example.asistenciaservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.asistenciaservice.dto.AsistenciaDto;
import com.example.asistenciaservice.entity.Asistencia;

@Mapper
public interface AsistenciaMapper {
	  AsistenciaMapper MAPPER = Mappers.getMapper(AsistenciaMapper.class);

	  AsistenciaDto mappToDto(Asistencia asistencia);

	  Asistencia mappToEntity(AsistenciaDto asistenciaDto);
}
