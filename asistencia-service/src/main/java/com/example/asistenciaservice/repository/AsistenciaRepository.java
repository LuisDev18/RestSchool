package com.example.asistenciaservice.repository;
import com.example.asistenciaservice.entity.Asistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer>{
	List<Asistencia> findByIdEstudiante(int idEstudiante);
}
