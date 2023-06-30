package utp.edu.pe.apirestschool.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.apirestschool.entity.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByEmailContaining(String email, Pageable page);
    Optional<Usuario> findByEmail(String email);


}
