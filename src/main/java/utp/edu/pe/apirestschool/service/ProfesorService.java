package utp.edu.pe.apirestschool.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import utp.edu.pe.apirestschool.entity.Profesor;

public interface ProfesorService {

  public List<Profesor> findAll(Pageable page);

  public List<Profesor> findByNombre(String nombre, Pageable page);

  public Profesor findById(int id);

  public Profesor save(Profesor profesor);

  public Profesor update(Profesor profesor);

  public void delete(int id);
}
