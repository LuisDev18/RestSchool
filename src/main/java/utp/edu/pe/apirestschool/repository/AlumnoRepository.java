package utp.edu.pe.apirestschool.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.pe.apirestschool.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

  List<Alumno> findByNombreContaining(String nombre, Pageable page);

  Alumno findByNombre(String nombre);

  Optional<Alumno> findByEmail(String email);
}
