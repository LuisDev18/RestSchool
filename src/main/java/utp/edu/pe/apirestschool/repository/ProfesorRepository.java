package utp.edu.pe.apirestschool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utp.edu.pe.apirestschool.entity.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor,Integer> {

    //Usando Derived Query method
    List<Profesor> findByNombreContaining(String nombre, Pageable page);
    Profesor findByNombre(String nombre);

    Optional<Profesor> findByEmail(String email);

}
