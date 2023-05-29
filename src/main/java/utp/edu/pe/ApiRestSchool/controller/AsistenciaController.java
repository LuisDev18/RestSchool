package utp.edu.pe.ApiRestSchool.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
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

import utp.edu.pe.ApiRestSchool.dto.AsistenciaDto;
import utp.edu.pe.ApiRestSchool.entity.Asistencia;
import utp.edu.pe.ApiRestSchool.mapper.AsistenciaMapper;
import utp.edu.pe.ApiRestSchool.service.AsistenciaService;

@RestController
@RequestMapping("/v1/asistencia")
public class AsistenciaController {
	@Autowired
	private AsistenciaService service;	

	@GetMapping(value = "byEstudiante/{idEstudiante}")
	public ResponseEntity<Object> findByEstudiante(@PathVariable("idEstudiante") int idEstudiante) {
		List<Asistencia> asistencias = service.findByEstudiante(idEstudiante);
		List<AsistenciaDto> asistenciasDto = asistencias.stream().map(
				(asistencia)->AsistenciaMapper.MAPPER.mappToDto(asistencia)).collect(Collectors.toList());
		
        Map<String, Object> map = new HashMap<String, Object>();        
        if (asistenciasDto.toArray().length==0) map.put("message", "No data found"); else map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", asistenciasDto);
		//return new ResponseEntity(asistenciasDto,HttpStatus.OK);        

        return new ResponseEntity<Object>(map,HttpStatus.OK);
	}

	@GetMapping(value = "/{idAsistencia}")
	public ResponseEntity<AsistenciaDto> findByIdAsistencia(@PathVariable("idAsistencia") int idAsistencia) {
		Asistencia registro = service.findByIdAsistencia(idAsistencia);
		AsistenciaDto registroDto=AsistenciaMapper.MAPPER.mappToDto(registro);
		
        Map<String, Object> map = new HashMap<String, Object>();        
        if (registroDto==null) map.put("message", "No data found"); else map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", registroDto);
		//return new ResponseEntity(registroDto,HttpStatus.OK);
        return new ResponseEntity(map,HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<AsistenciaDto> saveAsistencia(@Valid @RequestBody AsistenciaDto asistenciaDto) {
		Asistencia registro = service.saveAsistencia(AsistenciaMapper.MAPPER.mappToEntity(asistenciaDto));
		AsistenciaDto registroDto=AsistenciaMapper.MAPPER.mappToDto(registro);
		
        Map<String, Object> map = new HashMap<String, Object>();        
        map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", registroDto);
		//return ResponseEntity.status(HttpStatus.CREATED).body(registroDto);
        return new ResponseEntity(map,HttpStatus.CREATED);
	}

	@PutMapping(value = "/{idAsistencia}")
	public ResponseEntity<AsistenciaDto> updateAsistencia( @PathVariable("idAsistencia") int idAsistencia,
														   @Valid @RequestBody AsistenciaDto asistenciaDto) {
		Asistencia registro = service.updateAsistencia(AsistenciaMapper.MAPPER.mappToEntity(asistenciaDto));
		AsistenciaDto registroDto=AsistenciaMapper.MAPPER.mappToDto(registro);
		
        Map<String, Object> map = new HashMap<String, Object>();        
        map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", registroDto);
        
        return new ResponseEntity(map,HttpStatus.OK);
		//return new ResponseEntity(registroDto,HttpStatus.OK);
	}

	@DeleteMapping(value = "/{idAsistencia}")
	public ResponseEntity<Asistencia> deleteAsistencia(@PathVariable("idAsistencia") int idAsistencia) {
		service.deleteAsistencia(idAsistencia);
		
        Map<String, Object> map = new HashMap<String, Object>();        
        map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", null);
        return new ResponseEntity(map,HttpStatus.OK);
		//return new ResponseEntity(null,HttpStatus.OK);
	}
}
