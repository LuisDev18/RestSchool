package utp.edu.pe.ApiRestSchool.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.pe.ApiRestSchool.entity.Alumno;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    List<Alumno> findByNombreContaining(String nombre, Pageable page);
    Alumno findByNombre(String nombre);

}
