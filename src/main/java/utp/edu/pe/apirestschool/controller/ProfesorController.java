package utp.edu.pe.apirestschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import org.springframework.web.bind.annotation.RequestParam;
import utp.edu.pe.apirestschool.dto.ProfesorDto;
import utp.edu.pe.apirestschool.entity.Profesor;
import utp.edu.pe.apirestschool.mapper.ProfesorMapper;
import utp.edu.pe.apirestschool.service.ProfesorService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(
    name = "CRUD API REST para el modulo Profesor ",
    description =
        "CRUD REST API - Create Profesor, Update Profesor, Get Profesor, Get all Profesor, Delete Profesor")
@RestController
@RequestMapping(value = "api/v1/profesor")
public class ProfesorController {

  @Autowired private ProfesorService service;

  @Operation(
      summary = "Listar Profesores - API REST",
      description = "Permite listar un conjunto de profesores ")
  @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS")
  @GetMapping
  public ResponseEntity<List<ProfesorDto>> findAll(
      @RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
      @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
      @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    List<Profesor> profesor;

    if (nombre == null) {
      profesor = service.findAll(page);
    } else {
      profesor = service.findByNombre(nombre, page);
    }
    List<ProfesorDto> profesorDto =
        profesor.stream()
            .map((profe) -> ProfesorMapper.MAPPER.mappToDto(profe))
            .collect(Collectors.toList());

    return ResponseEntity.ok(profesorDto);
  }

  @Operation(
      summary = "Buscar Profesor por ID - API REST",
      description = "Permite buscar un profesor por ID en la base de datos")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS"),
        @ApiResponse(responseCode = "404", description = "RESOURCE NOT FOUND")
      })
  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<ProfesorDto> findById(@PathVariable("id") int id) {
    Profesor profesor = service.findById(id);

    ProfesorDto registroDto = ProfesorMapper.MAPPER.mappToDto(profesor);
    return ResponseEntity.ok(registroDto);
  }

  @Operation(
      summary = "Crear Profesor - API REST",
      description = "Permite aregar un nuevo profesor en la base de datos")
  @ApiResponse(responseCode = "201", description = "HTTP STATUS 201 CREATED")
  @PostMapping(produces = "application/json")
  public ResponseEntity<ProfesorDto> create(@Valid @RequestBody ProfesorDto profesorDto) {
    Profesor registro = service.save(ProfesorMapper.MAPPER.mappToEntity(profesorDto));
    ProfesorDto profesorCreado = ProfesorMapper.MAPPER.mappToDto(registro);
    return new ResponseEntity<>(profesorCreado, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Actualizar Profesor - API REST",
      description = "Permite actualizar los datos de un profesor")
  @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS")
  @PutMapping(value = "/{id}")
  public ResponseEntity<ProfesorDto> update(
      @PathVariable("id") int id, @Valid @RequestBody ProfesorDto profesorDto) {

    Profesor registro = service.update(ProfesorMapper.MAPPER.mappToEntity(profesorDto));
    ProfesorDto profesorUpdate = ProfesorMapper.MAPPER.mappToDto(registro);

    return new ResponseEntity<>(profesorUpdate, HttpStatus.OK);
  }

  @Operation(
      summary = "Eliminar Profesor -API REST",
      description = "Permite eliminar un profesor de la base de datos")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS"),
        @ApiResponse(responseCode = "404", description = "RESOURCE NOT FOUND")
      })
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") int id) {
    service.delete(id);
    return new ResponseEntity<>("Objeto eliminado", HttpStatus.OK);
  }
}
