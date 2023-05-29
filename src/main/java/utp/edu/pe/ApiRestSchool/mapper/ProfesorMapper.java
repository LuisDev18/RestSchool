package utp.edu.pe.ApiRestSchool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.ApiRestSchool.dto.ProfesorDto;
import utp.edu.pe.ApiRestSchool.entity.Profesor;

@Mapper
public interface ProfesorMapper {

    ProfesorMapper MAPPER = Mappers.getMapper(ProfesorMapper.class);

    ProfesorDto mappToDto(Profesor profesor);
    Profesor mappToEntity(ProfesorDto profesorDto);}
@Override
    public ProfesorDTO profesorToProfesorDto(Profesor profesorRepository) {
        if ( profesor == null ) {
            return null;
        }
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setNombre( profesor.getNombre() );
        profesorDTO.setApellidoPaterno( profesor.getApellidoPaterno() );
        profesorDTO.setApellidoMaterno( profesor.getApellidoMaterno() );
        profesorDTO.setDni( profesor.getDni() );
        profesorDTO.setEmail( profesor.getEmail() );
        profesorDTO.setCurso( profesor.getCurso() );
        return profesorDTO;
    }
}
