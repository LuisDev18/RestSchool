package utp.edu.pe.ApiRestSchool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.ApiRestSchool.dto.AlumnoDto;
import utp.edu.pe.ApiRestSchool.entity.Alumno;

@Mapper
public interface AlumnoMapper {
    AlumnoMapper MAPPER = Mappers.getMapper(AlumnoMapper.class);

    AlumnoDto mappToDto(Alumno alumno);

    Alumno mappToEntity(AlumnoDto alumnoDto);

}
