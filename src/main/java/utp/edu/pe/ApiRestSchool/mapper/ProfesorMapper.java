package utp.edu.pe.ApiRestSchool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.ApiRestSchool.dto.ProfesorDto;
import utp.edu.pe.ApiRestSchool.entity.Profesor;

@Mapper
public interface ProfesorMapper {

    ProfesorMapper MAPPER = Mappers.getMapper(ProfesorMapper.class);

    ProfesorDto mappToDto(Profesor profesor);
    Profesor mappToEntity(ProfesorDto profesorDto);



}
