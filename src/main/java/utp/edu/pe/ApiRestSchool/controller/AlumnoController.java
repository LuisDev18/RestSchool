package utp.edu.pe.ApiRestSchool.controller;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.ApiRestSchool.dto.AlumnoDto;
import utp.edu.pe.ApiRestSchool.entity.Alumno;
import utp.edu.pe.ApiRestSchool.mapper.AlumnoMapper;
import utp.edu.pe.ApiRestSchool.service.AlumnoService;
import utp.edu.pe.ApiRestSchool.util.WrapperResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService service;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<AlumnoDto> alumnosDto = service.findAll().stream().map(AlumnoMapper.MAPPER::mappToDto).toList();
        Map<String, Object> map = new HashMap<String, Object>();
        if (alumnosDto.toArray().length==0) map.put("message", "No data found"); else map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", alumnosDto);
        //return new ResponseEntity(alumnosDto,HttpStatus.OK);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<AlumnoDto>> findById(@PathVariable("id") int id) {
        Alumno registro = service.findById(id);
        AlumnoDto registroDto=AlumnoMapper.MAPPER.mappToDto(registro);

        Map<String, Object> map = new HashMap<String, Object>();
        if (registroDto==null) map.put("message", "No data found"); else map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", registroDto);
        //return new ResponseEntity(registroDto,HttpStatus.OK);
        return new ResponseEntity(map,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AlumnoDto> create(@RequestBody AlumnoDto alumnoDto) {
        Alumno registro = service.save(AlumnoMapper.MAPPER.mappToEntity(alumnoDto));
        AlumnoDto registroDto=AlumnoMapper.MAPPER.mappToDto(registro);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", registroDto);
        //return ResponseEntity.status(HttpStatus.CREATED).body(registroDto);
        return new ResponseEntity(map,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlumnoDto> update(@PathVariable("id") int id, @RequestBody AlumnoDto alumnoDto) {
        Alumno registro = service.update(AlumnoMapper.MAPPER.mappToEntity(alumnoDto));
        AlumnoDto registroDto=AlumnoMapper.MAPPER.mappToDto(registro);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", registroDto);

        return new ResponseEntity(map,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Alumno> delete(@PathVariable("id") int id) {
        service.delete(id);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "Success");
        map.put("status", HttpStatus.OK);
        map.put("data", null);
        return new ResponseEntity(map,HttpStatus.OK);
    }

}
