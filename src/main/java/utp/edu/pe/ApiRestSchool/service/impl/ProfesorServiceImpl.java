package utp.edu.pe.ApiRestSchool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.ApiRestSchool.dto.ProfesorDto;
import utp.edu.pe.ApiRestSchool.repository.ProfesorRepository;
import utp.edu.pe.ApiRestSchool.service.ProfesorService;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository){
        this.profesorRepository=profesorRepository;
    }
    @Override
    public ProfesorDto getProfesorById(Long id) {

        /*  Profesor profesor= profesorRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("User","id",id)
       );
       return ProfesorMapper.MAPPER.mappToDto(profesor);
        * */
        return null;
    }
}
