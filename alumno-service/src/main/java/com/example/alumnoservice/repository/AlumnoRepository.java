package com.example.alumnoservice.repository;

import com.example.alumnoservice.entity.Alumno;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    List<Alumno> findByNombreContaining(String nombre, Pageable page);

    Alumno findByNombre(String nombre);

    Optional<Alumno> findByEmail(String email);
}
