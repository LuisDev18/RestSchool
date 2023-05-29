package utp.edu.pe.ApiRestSchool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.utp.RestSchool.entity.Profesor;
import pe.edu.utp.RestSchool.service.ProfesorService;
@Tag(
        name="CRUD REST API para el modulo Profesor ",
        description = "CRUD REST API - Create Profesor, Update Profesor, Get Profesor, Get all Profesor, Delete Profesor"
)
@RestController
@RequestMapping(value="api/v1/profesor")
public class ProfesorController {
@Autowired
	private ProfesorService service;
	
	@Autowired
	private ProfesorConverter converter;
	
	@GetMapping
	public ResponseEntity<List<ProfesorDto>> findAll(
			@RequestParam(value="nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value="offset", required = false, defaultValue= "0") int pageNumber,
			@RequestParam(value="limit", required = false, defaultValue= "5") int pageSize
			){
				Pageable page=PageRequest.of(pageNumber,pageSize);
				List<Profesor> profesor;
				
				if(nombre==null) {
					profesor=service.findAll(page);
				}else {
					profesor=service.findByNombre(nombre, page);
				}
				List<ProfesorDto> articulosDto=converter.fromEntity(profesor);
				return ResponseEntity.ok(profesorDto);
			}
	@GetMapping(value="/{id}")
	public ResponseEntity<ProfesorDto> findById(@PathVariable("id") int id){
		Profesor registro=service.findById(id);
		ProfesorDto registroDto=converter.fromEntity(register);
		return ResponseEntity.ok(registroDto);
	}
	@PostMapping()
	public ResponseEntity<ProfesorDto> create(@RequestBody ProfesorDto profesorDto){
		Profesor registro=service.save(converter.fromDTO(profesorDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(converter.fromEntity(registro));
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<Profesor> update(@PathVariable("id") int id, @RequestParam ProfesorDto profesorDto){
		Profesor registro=service.update(converter.fromDTO(profesorDto));
		return ResponseEntity.ok(converter.fromEntityt(registro));
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Profesor> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.ok(null);
	}
	
	
}
