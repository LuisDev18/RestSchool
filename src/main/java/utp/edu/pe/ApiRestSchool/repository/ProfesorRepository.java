package utp.edu.pe.ApiRestSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor,Integer> {

    //Usando Derived Query method
    List<Profesor> findByNombreContaining(String nombre, Pageable page);
    Profesor findByNombre(String nombre);

}
