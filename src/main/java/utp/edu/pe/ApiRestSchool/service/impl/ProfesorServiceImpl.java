package utp.edu.pe.ApiRestSchool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.ApiRestSchool.dto.ProfesorDto;
import utp.edu.pe.ApiRestSchool.repository.ProfesorRepository;
import utp.edu.pe.ApiRestSchool.service.ProfesorService;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository){
        this.profesorRepository=profesorRepository;
    }
    @Override
    public ProfesorDto getProfesorById(Long id) {

        /*  Profesor profesor= profesorRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("User","id",id)
       );
       return ProfesorMapper.MAPPER.mappToDto(profesor);
        * */
        return null;
    }
    @Override
	@Transactional 	(readOnly = true)
	public List<Profesor> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch(ValidateServiceException | NoDataFoundException e) {
			Log.info(e.getMessage());
			throw e;
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
			return null;
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Profesor> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre, page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			Log.info(e.getMessage());
			throw e;
		}catch (Exception e) {
			Log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e); 
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findById(int id) {
		try {
			return repository.findById(id).orElseThrow(()->newDataFoundException("No existe el registro con el ID"+id));
		} catch (ValidateServiceException | NoDataFoundException e) {
			Log.info(e.getMessage());
			throw e;
		}catch (Exception e) {   //return ProfesorMapper.MAPPER.mappToDto(profesor);
			Log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e); 
		}
	}

	@Override
	@Transactional
	public Profesor save(Profesor profesor) {
		try {
			ProfesorValidator.save(profesor);
			return repository.save(profesor);
		} catch (ValidateServiceException | GeneralServiceException e) {
			Log.info(e.getMessage());
			throw e;
		}catch (Exception e) {
			Log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e); 
		}
	}
	
	@Override
	@Transactional
	public Profesor update(Profesor profesor) {
		
		try {
			ProfesorValidator.save(profesor);
			Profesor registro=repository.findById(profesor.getId())
					.orElseThrow(()->newDataFoundException("No existe el registro con el ID"+profesor.getId()));
			registro.setNombre(profesor.getNombre());
			registro.setApellidoPaterno(profesor.getApellidoPaterno());
			registro.setApellidoMaterno(profesor.getApellidoMaterno());
			registro.setDni(profesor.getDni());
			registro.setCelular(profesor.getCelular());
			registro.setEmail(profesor.getEmail());
			registro.setCurso(profesor.getCurso());
			return repository.save(registro);
		} catch (ValidateServiceException | NoDataFoundException e) {  //return ProfesorMapper.MAPPER.mappToDto(profesor);
			log.info(e.getMessage());
			throw e;
		}catch(Exception e) {
			Log.error(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Profesor registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con el ID"+id));
			repository.delete(registro);
		} catch(ValidateServiceException | NoDataFoundException e) { //return ProfesorMapper.MAPPER.mappToDto(profesor);
			Log.info(e.getMessage());
			throw e;
		}catch (Exception e) {
			Log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}
}
