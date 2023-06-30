package utp.edu.pe.apirestschool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.apirestschool.entity.Alumno;
import utp.edu.pe.apirestschool.exception.EmailAlreadyExistsException;
import utp.edu.pe.apirestschool.exception.ResourceNotFoundException;
import utp.edu.pe.apirestschool.repository.AlumnoRepository;
import utp.edu.pe.apirestschool.service.AlumnoService;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AlumnoServiceImpl implements AlumnoService {

  @Autowired private AlumnoRepository repository;

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
  @Transactional(readOnly = true)
  public List<Alumno> findByNombre(String nombre, Pageable page) {
    try {
      return repository.findByNombreContaining(nombre, page);
    } catch (ResourceNotFoundException e) {
      log.info(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Alumno findById(int id) {
    Alumno alumno =
        repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("alumno", "id", Long.valueOf(id)));
    return alumno;
  }

  @Override
  @Transactional
  public Alumno save(Alumno alumno) {
    Optional<Alumno> emailAlumno = repository.findByEmail(alumno.getEmail());
    if (emailAlumno.isPresent()) {
      throw new EmailAlreadyExistsException("El email ya esta siendo usado por otro alumno");
    }
    Alumno alumnoSave = repository.save(alumno);
    return alumnoSave;
  }

  @Override
  @Transactional
  public Alumno update(Alumno alumno) {
    try {
      save(alumno);
      Alumno registroD = repository.findByNombre(alumno.getNombre());
      if (registroD != null && registroD.getId() != alumno.getId()) {
        throw new ResourceNotFoundException("Alumno", "id", Long.valueOf(alumno.getId()));
      }
      Alumno registro = repository.findById(alumno.getId()).orElseThrow(null);
      registro.setNombre(alumno.getNombre());
      registro.setApellidoPaterno(alumno.getApellidoPaterno());
      registro.setApellidoMaterno(alumno.getApellidoMaterno());
      registro.setDni(alumno.getDni());
      registro.setCelular(alumno.getCelular());
      registro.setEmail(alumno.getEmail());
      registro.setGenero(alumno.getGenero());
      registro.setPadreApoderado(alumno.getPadreApoderado());
      registro.setDireccion(alumno.getDireccion());
      return repository.save(registro);
    } catch (ResourceNotFoundException e) {
      log.info(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  @Override
  @Transactional
  public void delete(int id) {

    Alumno registro =
        repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Alumno", "id", Long.valueOf(id)));
    repository.delete(registro);
  }
}
