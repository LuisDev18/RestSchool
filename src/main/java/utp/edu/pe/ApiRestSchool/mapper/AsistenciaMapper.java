package utp.edu.pe.ApiRestSchool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.ApiRestSchool.dto.AsistenciaDto;
import utp.edu.pe.ApiRestSchool.entity.Asistencia;

@Mapper
public interface AsistenciaMapper {

    AsistenciaMapper MAPPER = Mappers.getMapper(AsistenciaMapper.class);

    AsistenciaDto mappToDto (Asistencia asistencia);

    Asistencia mappToEntity (AsistenciaDto asistenciaDto);
}
