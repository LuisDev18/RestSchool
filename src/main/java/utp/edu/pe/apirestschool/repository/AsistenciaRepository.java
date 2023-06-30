package utp.edu.pe.apirestschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utp.edu.pe.apirestschool.entity.Asistencia;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
	List<Asistencia> findByIdEstudiante(int idEstudiante);

}

