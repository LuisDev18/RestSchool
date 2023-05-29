package utp.edu.pe.ApiRestSchool.service;


import org.springframework.data.domain.Pageable;
import utp.edu.pe.ApiRestSchool.entity.Alumno;

import java.util.List;

public interface AlumnoService {
    public List<Alumno> findAll();
    public List<Alumno> findByNombre(String nombre, Pageable page);
    public Alumno findById(int id);
    public Alumno save(Alumno alumno);
    public Alumno update(Alumno alumno);
    public void delete(int id);
}
