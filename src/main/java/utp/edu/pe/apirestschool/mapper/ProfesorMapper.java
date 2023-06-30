package utp.edu.pe.apirestschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.apirestschool.dto.ProfesorDto;
import utp.edu.pe.apirestschool.entity.Profesor;

@Mapper
public interface ProfesorMapper {

  ProfesorMapper MAPPER = Mappers.getMapper(ProfesorMapper.class);

  ProfesorDto mappToDto(Profesor profesor);

  Profesor mappToEntity(ProfesorDto profesorDto);
}
