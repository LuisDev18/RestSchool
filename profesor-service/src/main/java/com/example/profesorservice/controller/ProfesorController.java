package com.example.profesorservice.controller;

import com.example.profesorservice.dto.ApiResponseDto;
import com.example.profesorservice.dto.ProfesorDto;
import com.example.profesorservice.entity.Profesor;
import com.example.profesorservice.mapper.ProfesorMapper;
import com.example.profesorservice.service.ProfesorService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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

@RestController
@RequestMapping(value = "/v1/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService service;


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


    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ApiResponseDto> findById(@PathVariable("id") int id) {
        ApiResponseDto profesor = service.findById(id);
        return ResponseEntity.ok(profesor);
    }


    @PostMapping
    public ResponseEntity<ProfesorDto> create(@Valid @RequestBody ProfesorDto profesorDto) {
        Profesor registro = service.save(ProfesorMapper.MAPPER.mappToEntity(profesorDto));
        ProfesorDto profesorCreado = ProfesorMapper.MAPPER.mappToDto(registro);
        return new ResponseEntity<>(profesorCreado, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfesorDto> update(
            @PathVariable("id") int id, @Valid @RequestBody ProfesorDto profesorDto) {

        Profesor registro = service.update(ProfesorMapper.MAPPER.mappToEntity(profesorDto));
        ProfesorDto profesorUpdate = ProfesorMapper.MAPPER.mappToDto(registro);

        return new ResponseEntity<>(profesorUpdate, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new ResponseEntity<>("Objeto eliminado", HttpStatus.OK);
    }
}
