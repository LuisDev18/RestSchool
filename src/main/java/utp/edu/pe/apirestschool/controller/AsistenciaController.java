package utp.edu.pe.apirestschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
import utp.edu.pe.apirestschool.dto.AsistenciaDto;
import utp.edu.pe.apirestschool.entity.Asistencia;
import utp.edu.pe.apirestschool.mapper.AsistenciaMapper;
import utp.edu.pe.apirestschool.service.AsistenciaService;

@Tag(name = "CRUD API REST para el modulo Asistencia ", description = "CRUD REST API")
@RestController
@RequestMapping("api/v1/asistencia")
public class AsistenciaController {
  @Autowired private AsistenciaService service;

  @GetMapping(value = "byEstudiante/{idEstudiante}")
  public ResponseEntity<Object> findByEstudiante(@PathVariable("idEstudiante") int idEstudiante) {
    List<Asistencia> asistencias = service.findByEstudiante(idEstudiante);
    List<AsistenciaDto> asistenciasDto =
        asistencias.stream()
            .map((asistencia) -> AsistenciaMapper.MAPPER.mappToDto(asistencia))
            .collect(Collectors.toList());

    Map<String, Object> map = new HashMap<String, Object>();
    if (asistenciasDto.toArray().length == 0) {
      map.put("message", "No data found");
    } else{
      map.put("message", "Success");
    }
    map.put("status", HttpStatus.OK);
    map.put("data", asistenciasDto);
    return new ResponseEntity<Object>(map, HttpStatus.OK);
  }

  @GetMapping(value = "/{idAsistencia}", produces = "application/json")
  public ResponseEntity<AsistenciaDto> findByIdAsistencia(
      @PathVariable("idAsistencia") int idAsistencia) {
    Asistencia registro = service.findByIdAsistencia(idAsistencia);
    AsistenciaDto registroDto = AsistenciaMapper.MAPPER.mappToDto(registro);

    Map<String, Object> map = new HashMap<String, Object>();
    if (registroDto == null){
      map.put("message", "No data found");
    }
    else{
      map.put("message", "Success");
    }
    map.put("status", HttpStatus.OK);
    map.put("data", registroDto);
    return new ResponseEntity(map, HttpStatus.OK);
  }

  @Operation(
      summary = "Crear Asistencia - API REST",
      description = "Permite registrar una nueva asistencia en la base de datos")
  @ApiResponse(responseCode = "201", description = "HTTP STATUS 201 CREATED")
  @PostMapping(produces = "application/json")
  public ResponseEntity<AsistenciaDto> saveAsistencia(
      @Valid @RequestBody AsistenciaDto asistenciaDto) {
    Asistencia registro =
        service.saveAsistencia(AsistenciaMapper.MAPPER.mappToEntity(asistenciaDto));
    AsistenciaDto registroDto = AsistenciaMapper.MAPPER.mappToDto(registro);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", "Success");
    map.put("status", HttpStatus.OK);
    map.put("data", registroDto);
    return new ResponseEntity(map, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Actualizar Asistencia - API REST",
      description = "Permite actualizar los datos de una asistencia")
  @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS")
  @PutMapping(value = "/{idAsistencia}", produces = "application/json")
  public ResponseEntity<AsistenciaDto> updateAsistencia(
      @PathVariable("idAsistencia") int idAsistencia,
      @Valid @RequestBody AsistenciaDto asistenciaDto) {
    Asistencia registro =
        service.updateAsistencia(AsistenciaMapper.MAPPER.mappToEntity(asistenciaDto));
    AsistenciaDto registroDto = AsistenciaMapper.MAPPER.mappToDto(registro);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", "Success");
    map.put("status", HttpStatus.OK);
    map.put("data", registroDto);

    return new ResponseEntity(map, HttpStatus.OK);
  }

  @Operation(
      summary = "Delete Asistencia REST API",
      description =
          "Delete Asistencia es usado para eliminar un registro de asistencia de la base de datos")
  @ApiResponse(responseCode = "200", description = "HTTP STATUS 200 SUCCESS")
  @DeleteMapping(value = "/{idAsistencia}")
  public ResponseEntity<Asistencia> deleteAsistencia(
      @PathVariable("idAsistencia") int idAsistencia) {
    service.deleteAsistencia(idAsistencia);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", "Success");
    map.put("status", HttpStatus.OK);
    map.put("data", null);
    return new ResponseEntity(map, HttpStatus.OK);
  }
}
