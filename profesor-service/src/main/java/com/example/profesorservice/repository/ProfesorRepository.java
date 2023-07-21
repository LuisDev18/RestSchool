package com.example.profesorservice.repository;

import com.example.profesorservice.entity.Profesor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    // Usando Derived Query method
    List<Profesor> findByNombreContaining(String nombre, Pageable page);

    Profesor findByNombre(String nombre);

    Optional<Profesor> findByEmail(String email);
}
