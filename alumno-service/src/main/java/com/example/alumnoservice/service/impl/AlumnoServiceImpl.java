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
}
