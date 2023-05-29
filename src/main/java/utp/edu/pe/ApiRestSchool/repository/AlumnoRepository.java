package utp.edu.pe.ApiRestSchool.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import utp.edu.pe.ApiRestSchool.entity.Alumno;

public interface AlumnoRepository  extends JpaRepository<Alumno, Integer>{
	List<Alumno> findByNombreContaining(String nombre, Pageable page);
}
