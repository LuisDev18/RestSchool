package com.example.profesorservice.service.impl;

import com.example.profesorservice.dto.AlumnoDto;
import com.example.profesorservice.dto.AlumnoResponseDto;
import com.example.profesorservice.dto.ApiResponseDto;
import com.example.profesorservice.entity.Profesor;
import com.example.profesorservice.exception.EmailAlreadyExistsException;
import com.example.profesorservice.exception.ResourceNotFoundException;
import com.example.profesorservice.mapper.ProfesorMapper;
import com.example.profesorservice.repository.ProfesorRepository;
import com.example.profesorservice.service.ProfesorService;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class ProfesorServiceImpl implements ProfesorService {
  @Autowired private ProfesorRepository profesorRepository;
  @Autowired private WebClient webClient;

  @Autowired
  public ProfesorServiceImpl(ProfesorRepository profesorRepository) {

    this.profesorRepository = profesorRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Profesor> findAll(Pageable page) {
    try {
      return profesorRepository.findAll(page).toList();
    } catch (ResourceNotFoundException e) {
      log.info(e.getMessage());
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<Profesor> findByNombre(String nombre, Pageable page) {
    try {
      return profesorRepository.findByNombreContaining(nombre, page);
    } catch (ResourceNotFoundException e) {
      log.info(e.getMessage());
      throw e;
    }
  }

  @Override
  public ApiResponseDto findById(int id) {
    Profesor profesor =
        profesorRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("profesor", "id", Long.valueOf(id)));

    // Ejemplo de uso de WebClient
    AlumnoResponseDto response =
        webClient
            .get()
            .uri("http://localhost:8083/api/v1/alumnos")
            .retrieve()
            .bodyToMono(AlumnoResponseDto.class)
            .block();

    List<AlumnoDto> alumnoDto = response.getData();
    ApiResponseDto apiResponseDto = new ApiResponseDto();
    apiResponseDto.setAlumnoDto(alumnoDto);
    apiResponseDto.setProfesorDto(ProfesorMapper.MAPPER.mappToDto(profesor));

    return apiResponseDto;
  }

  @Override
  @Transactional
  public Profesor save(Profesor profesor) {

    Optional<Profesor> optionalProfesor = profesorRepository.findByEmail(profesor.getEmail());
    if (optionalProfesor.isPresent()) {
      throw new EmailAlreadyExistsException("Email ya esta siendo usado por otro usuario");
    }
    Profesor profesorSave = profesorRepository.save(profesor);

    return profesorSave;
  }

  @Override
  @Transactional
  public Profesor update(Profesor profesor) {

    Profesor registro =
        profesorRepository
            .findById(profesor.getId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "profesor", "id", Long.valueOf(profesor.getId())));

    registro.setNombre(profesor.getNombre());
    registro.setApellidoPaterno(profesor.getApellidoPaterno());
    registro.setApellidoMaterno(profesor.getApellidoMaterno());
    registro.setDni(profesor.getDni());
    registro.setCelular(profesor.getCelular());
    registro.setEmail(profesor.getEmail());
    registro.setCurso(profesor.getCurso());
    return profesorRepository.save(registro);
  }

  @Override
  @Transactional
  public void delete(int id) {

    Profesor registro =
        profesorRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("profesor", "id", Long.valueOf(id)));
    profesorRepository.delete(registro);
  }
}
