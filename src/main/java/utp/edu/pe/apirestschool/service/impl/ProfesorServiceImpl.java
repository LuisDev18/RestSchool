package utp.edu.pe.apirestschool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.apirestschool.entity.Profesor;
import utp.edu.pe.apirestschool.exception.EmailAlreadyExistsException;
import utp.edu.pe.apirestschool.exception.ResourceNotFoundException;
import utp.edu.pe.apirestschool.repository.ProfesorRepository;
import utp.edu.pe.apirestschool.service.ProfesorService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository){

		this.profesorRepository=profesorRepository;
    }

    @Override
	@Transactional(readOnly = true)
	public List<Profesor> findAll(Pageable page) {
		try {
			return profesorRepository.findAll(page).toList();
		} catch( ResourceNotFoundException e) {
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
	public Profesor findById(int id) {
	Profesor profesor=	profesorRepository.findById(id).orElseThrow(()->
				new ResourceNotFoundException("profesor","id",Long.valueOf(id)));
		return profesor;
	}


	@Override
	@Transactional
	public Profesor save(Profesor profesor) {

			Optional<Profesor> optionalProfesor = profesorRepository.findByEmail(profesor.getEmail());
			if(optionalProfesor.isPresent()){
				throw new EmailAlreadyExistsException("Email ya esta siendo usado por otro usuario");
			}
			Profesor profesorSave= profesorRepository.save(profesor);

			return profesorSave;
	}
	
	@Override
	@Transactional
	public Profesor update(Profesor profesor) {

			Profesor registro=profesorRepository.findById(profesor.getId())
					.orElseThrow(()->new ResourceNotFoundException("profesor","id",Long.valueOf(profesor.getId())));

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

			Profesor registro=profesorRepository.findById(id).orElseThrow(
					()->new ResourceNotFoundException("profesor","id",Long.valueOf(id))
			);
			profesorRepository.delete(registro);


	}
}
