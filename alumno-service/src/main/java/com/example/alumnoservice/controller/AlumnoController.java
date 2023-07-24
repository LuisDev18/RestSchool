package com.example.alumnoservice.controller;

import com.example.alumnoservice.dto.AlumnoDto;
import com.example.alumnoservice.entity.Alumno;
import com.example.alumnoservice.mapper.AlumnoMapper;
import com.example.alumnoservice.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService service;

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

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AlumnoDto> findById(
            @PathVariable("id") int id) {
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

    @PostMapping
    public ResponseEntity<AlumnoDto> create(@Valid @RequestBody AlumnoDto alumnoDto) {
        Alumno registro = service.save(AlumnoMapper.MAPPER.mappToEntity(alumnoDto));
        AlumnoDto alumnoCreado = AlumnoMapper.MAPPER.mappToDto(registro);
        return new ResponseEntity<>(alumnoCreado, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlumnoDto> update(
            @PathVariable("id") int id, @Valid @RequestBody AlumnoDto alumnoDto) {

        Alumno registro = service.update(AlumnoMapper.MAPPER.mappToEntity(alumnoDto));
        AlumnoDto alumnoUpdate = AlumnoMapper.MAPPER.mappToDto(registro);

        return new ResponseEntity<>(alumnoUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new ResponseEntity<>("Objeto eliminado", HttpStatus.OK);
    }
}
