package com.example.alumnoservice.service;

import com.example.alumnoservice.entity.Alumno;

import java.util.List;

public interface AlumnoService {
    public List<Alumno> findAll();

    public Alumno findById(int id);

    public Alumno save(Alumno alumno);

    public Alumno update(Alumno alumno);

    public void delete(int id);

}
