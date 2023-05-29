package utp.edu.pe.ApiRestSchool.service;
import java.util.List;

import pe.edu.utp.ApiRestSchool.entity.Profesor;


import org.springframework.data.domain.Pageable;
import utp.edu.pe.ApiRestSchool.dto.ProfesorDto;

public interface ProfesorService {

    ProfesorDto getProfesorById(Long id);
     public List<Profesor> findAll(Pageable page);
	public List<Profesor> findByNombre(String nombre, Pageable page);
	public Profesor findById(int id);
	public Profesor save(Profesor profesor);
	public Profesor update(Profesor profesor);
	public void delete(int id);
}
