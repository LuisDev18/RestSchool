package com.example.alumnoservice.controller;

import com.example.alumnoservice.dto.AlumnoDto;
import com.example.alumnoservice.mapper.AlumnoMapper;
import com.example.alumnoservice.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
