package utp.edu.pe.apirestschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.apirestschool.dto.AlumnoDto;
import utp.edu.pe.apirestschool.entity.Alumno;
import utp.edu.pe.apirestschool.mapper.AlumnoMapper;
import utp.edu.pe.apirestschool.service.AlumnoService;
import utp.edu.pe.apirestschool.util.WrapperResponse;

@Tag(name = "CRUD API REST para el modulo Alumnos")
@RestController
@RequestMapping("/v1/alumnos")
public class AlumnoController {

  @Autowired private AlumnoService service;

  @Operation(
      summary = "Listar Alumnos - API REST",
      description = "Permite listar un conjunto de alumnos ")
  @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS")
  @GetMapping(produces = "application/json")
  public ResponseEntity<Object> findAll() {
    List<AlumnoDto> alumnosDto =
        service.findAll().stream().map(AlumnoMapper.MAPPER::mappToDto).toList();
    Map<String, Object> map = new HashMap<String, Object>();
    if (alumnosDto.toArray().length == 0) {
      map.put("message", "No data found");
    } else {
      map.put("message", "Success");
    }
    map.put("status", HttpStatus.OK);
    map.put("data", alumnosDto);
    // return new ResponseEntity(alumnosDto,HttpStatus.OK);

    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @Operation(
      summary = "Buscar Alumno por ID - API REST",
      description = "Permite buscar un alumnor por ID en la base de datos")
  @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS")
  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<WrapperResponse<AlumnoDto>> findById(@PathVariable("id") int id) {
    Alumno registro = service.findById(id);
    AlumnoDto registroDto = AlumnoMapper.MAPPER.mappToDto(registro);

    Map<String, Object> map = new HashMap<String, Object>();
    if (registroDto == null) {
      map.put("message", "No data found");
    } else {
      map.put("message", "Success");
    }
    map.put("status", HttpStatus.OK);
    map.put("data", registroDto);
    return new ResponseEntity(map, HttpStatus.OK);
  }

  @Operation(
      summary = "Crear Alumno - API REST",
      description = "Permite registrar una nuevo alumno en la base de datos")
  @ApiResponse(responseCode = "201", description = "HTTP STATUS 201 CREATED")
  @PostMapping(produces = "application/json")
  public ResponseEntity<AlumnoDto> create(@Valid @RequestBody AlumnoDto alumnoDto) {
    Alumno registro = service.save(AlumnoMapper.MAPPER.mappToEntity(alumnoDto));
    AlumnoDto registroDto = AlumnoMapper.MAPPER.mappToDto(registro);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", "Success");
    map.put("status", HttpStatus.CREATED);
    map.put("data", registroDto);
    // return ResponseEntity.status(HttpStatus.CREATED).body(registroDto);
    return new ResponseEntity(map, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Actualizar Alumno - API REST",
      description = "Permite actualizar los datos de un alumno")
  @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS")
  @PutMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<AlumnoDto> update(
      @PathVariable("id") int id, @Valid @RequestBody AlumnoDto alumnoDto) {
    Alumno registro = service.update(AlumnoMapper.MAPPER.mappToEntity(alumnoDto));
    AlumnoDto registroDto = AlumnoMapper.MAPPER.mappToDto(registro);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", "Success");
    map.put("status", HttpStatus.OK);
    map.put("data", registroDto);

    return new ResponseEntity(map, HttpStatus.OK);
  }

  @Operation(
      summary = "Delete Alumno REST API",
      description =
          "Delete Alumno es usado para eliminar un registro de alumno de la base de datos")
  @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Alumno> delete(@PathVariable("id") int id) {
    service.delete(id);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", "Success");
    map.put("status", HttpStatus.OK);
    map.put("data", null);
    return new ResponseEntity(map, HttpStatus.OK);
  }
}
