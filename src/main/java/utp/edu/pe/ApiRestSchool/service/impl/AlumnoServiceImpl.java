package utp.edu.pe.ApiRestSchool.service.impl;

import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utp.edu.pe.ApiRestSchool.dto.AlumnoDto;
import utp.edu.pe.ApiRestSchool.entity.Alumno;
import utp.edu.pe.ApiRestSchool.exception.ResourceNotFoundException;
import utp.edu.pe.ApiRestSchool.repository.AlumnoRepository;
import utp.edu.pe.ApiRestSchool.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Slf4j
@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository repository;
    public List<Alumno> findAll() {
        try {
            return repository.findAll();
        }catch(ResourceNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombre(String nombre, Pageable page) {
        try {
            return repository.findByNombreContaining(nombre,page);
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
        try {
            return repository.findById(id).orElseThrow(null);
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
    public Alumno save(Alumno alumno) {
        try {
            Alumno registro =repository.save(alumno);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Alumno update(Alumno alumno) {
        try {
            save(alumno);
            Alumno registroD = repository.findByNombre(alumno.getNombre());
            if(registroD !=null && registroD.getId()!= alumno.getId()) {
                throw new ResourceNotFoundException("User","id", Long.valueOf(alumno.getId()));
            }
            Alumno registro=repository.findById(alumno.getId())
                    .orElseThrow(null);
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
        } catch(ResourceNotFoundException e) {
            log.info(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Alumno registro=repository.findById(id).orElseThrow(null);
            repository.delete(registro);
        } catch(ResourceNotFoundException e) {
            log.info(e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
