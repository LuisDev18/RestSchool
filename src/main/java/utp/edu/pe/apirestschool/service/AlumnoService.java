package utp.edu.pe.apirestschool.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import utp.edu.pe.apirestschool.entity.Alumno;



public interface AlumnoService {
  public List<Alumno> findAll();

  public List<Alumno> findByNombre(String nombre, Pageable page);

  public Alumno findById(int id);

  public Alumno save(Alumno alumno);

  public Alumno update(Alumno alumno);

  public void delete(int id);
}
