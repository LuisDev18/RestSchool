package utp.edu.pe.apirestschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.apirestschool.dto.AsistenciaDto;
import utp.edu.pe.apirestschool.entity.Asistencia;

@Mapper
public interface AsistenciaMapper {

  AsistenciaMapper MAPPER = Mappers.getMapper(AsistenciaMapper.class);

  AsistenciaDto mappToDto(Asistencia asistencia);
  Asistencia mappToEntity(AsistenciaDto asistenciaDto);
}
