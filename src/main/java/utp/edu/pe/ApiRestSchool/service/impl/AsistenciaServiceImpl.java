package utp.edu.pe.ApiRestSchool.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import utp.edu.pe.ApiRestSchool.entity.Asistencia;
import utp.edu.pe.ApiRestSchool.exception.GlobalExceptionHandler;
import utp.edu.pe.ApiRestSchool.exception.ResourceNotFoundException;
import utp.edu.pe.ApiRestSchool.repository.AsistenciaRepository;
import utp.edu.pe.ApiRestSchool.service.AsistenciaService;

@Slf4j
@Service
public class AsistenciaServiceImpl implements AsistenciaService {
	@Autowired
	private AsistenciaRepository repository;

	
	
	@Override
	@Transactional(readOnly = true)
	public List<Asistencia> findByEstudiante(int idEstudiante) {
		try {
			List<Asistencia> registros=repository.findByIdEstudiante(idEstudiante);			
			return registros;
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
	public Asistencia findByIdAsistencia(int idAsistencia) {

			Asistencia registro = repository.findById(idAsistencia).orElseThrow(()->
					new ResourceNotFoundException("Asistencia","idAsistencia",Long.valueOf(idAsistencia)));
			return registro;

	}

	@Override
	@Transactional
	public Asistencia saveAsistencia(Asistencia asistencia) {

			Asistencia registro =repository.save(asistencia);			
			return registro;

	}

	@Override
	@Transactional
	public Asistencia updateAsistencia(Asistencia asistencia) {
		try {
			Asistencia asistenciaU = repository.findById(asistencia.getIdAsistencia()).orElseThrow(()-> new ResourceNotFoundException("Asistencia","idAsistencia",Long.valueOf(asistencia.getIdAsistencia())));

			asistenciaU.setIdClase(asistencia.getIdClase());
			asistenciaU.setIdEstudiante(asistencia.getIdEstudiante());
			asistenciaU.setFechaAsistencia(asistencia.getFechaAsistencia());
			asistenciaU.setObservacion(asistencia.getObservacion());
			asistenciaU.setAuxiliar(asistencia.getAuxiliar());
			
			Asistencia registro =repository.save(asistenciaU);						
			return registro;
		}catch(ResourceNotFoundException e){
			log.info(e.getMessage());
 
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	@Transactional
	public void deleteAsistencia(int idAsistencia) {
		try {
			Asistencia asistencia = repository.findById(idAsistencia).orElseThrow(()-> new ResourceNotFoundException("Asistencia","idAsistencia",Long.valueOf(idAsistencia)));
			repository.delete(asistencia);
		}catch(ResourceNotFoundException e){
			log.info(e.getMessage());
 
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
