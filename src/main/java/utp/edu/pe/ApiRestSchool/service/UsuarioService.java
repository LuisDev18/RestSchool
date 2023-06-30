package utp.edu.pe.ApiRestSchool.service;

import org.springframework.data.domain.Pageable;
import utp.edu.pe.ApiRestSchool.dto.LoginRequestDto;
import utp.edu.pe.ApiRestSchool.dto.LoginResponseDto;
import utp.edu.pe.ApiRestSchool.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    public List<Usuario> findAll(Pageable page);
    public List<Usuario> findByEmail(String email, Pageable page);
    public Usuario findById(int id);
    public Usuario update(Usuario usuario);
    public Usuario save(Usuario usuario);
    public void delete(int id);
    public LoginResponseDto login(LoginRequestDto loginRequestDto);
}
