package com.example.profesorservice.service;

import com.example.profesorservice.dto.ApiResponseDto;
import com.example.profesorservice.entity.Profesor;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfesorService {

    public List<Profesor> findAll(Pageable page);

    public List<Profesor> findByNombre(String nombre, Pageable page);

    public ApiResponseDto findById(int id);

    public Profesor save(Profesor profesor);

    public Profesor update(Profesor profesor);

    public void delete(int id);
}
