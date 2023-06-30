package utp.edu.pe.apirestschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.apirestschool.dto.AlumnoDto;
import utp.edu.pe.apirestschool.entity.Alumno;

@Mapper
public interface AlumnoMapper {
  AlumnoMapper MAPPER = Mappers.getMapper(AlumnoMapper.class);

  AlumnoDto mappToDto(Alumno alumno);
  Alumno mappToEntity(AlumnoDto alumnoDto);
}
