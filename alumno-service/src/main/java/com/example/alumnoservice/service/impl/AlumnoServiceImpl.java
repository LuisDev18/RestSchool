package com.example.alumnoservice.service.impl;

import com.example.alumnoservice.entity.Alumno;
import com.example.alumnoservice.exception.ResourceNotFoundException;
import com.example.alumnoservice.repository.AlumnoRepository;
import com.example.alumnoservice.service.AlumnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository repository;

  @Override
  public List<Alumno> findAll() {
    try {
      return repository.findAll();
    } catch (ResourceNotFoundException e) {
      log.info(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
        }

    @Override
    public Alumno findById(int id) {
        Alumno registro =
                repository
                        .findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Alumno", "idAlumno", Long.valueOf(id)));
        return registro;
    }

    @Override
    public Alumno save(Alumno alumno) {
        Alumno registro = repository.save(alumno);
        return registro;
    }

    @Override
    public Alumno update(Alumno alumno) {
        try {
            Alumno alumnoU =
                    repository
                            .findById(alumno.getId())
                            .orElseThrow(
                                    () ->
                                            new ResourceNotFoundException(
                                                    "Alumno",
                                                    "idAlumno",
                                                    Long.valueOf(alumno.getId())));

            alumnoU.setDni(alumno.getDni());
            alumnoU.setCelular(alumno.getCelular());
            alumnoU.setEmail(alumno.getEmail());
            alumnoU.setApellidoPaterno(alumno.getApellidoPaterno());
            alumnoU.setNombre(alumno.getNombre());
            alumnoU.setApellidoMaterno(alumno.getApellidoMaterno());
            alumnoU.setGenero(alumno.getGenero());
            alumnoU.setDireccion(alumno.getDireccion());


            Alumno registro = repository.save(alumnoU);
            return registro;
        } catch (ResourceNotFoundException e) {
            log.info(e.getMessage());

            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        try {
            Alumno alumno =
                    repository
                            .findById(id)
                            .orElseThrow(
                                    () ->
                                            new ResourceNotFoundException(
                                                    "Alumno", "idAlumno", Long.valueOf(id)));
            repository.delete(alumno);
        } catch (ResourceNotFoundException e) {
            log.info(e.getMessage());

            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
