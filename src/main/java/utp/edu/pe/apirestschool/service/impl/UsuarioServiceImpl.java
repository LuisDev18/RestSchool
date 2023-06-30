package utp.edu.pe.apirestschool.service.impl;

import io.jsonwebtoken.JwtException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.apirestschool.dto.LoginRequestDto;
import utp.edu.pe.apirestschool.dto.LoginResponseDto;
import utp.edu.pe.apirestschool.entity.Usuario;
import utp.edu.pe.apirestschool.exception.userexception.GeneralServiceException;
import utp.edu.pe.apirestschool.exception.userexception.NoDataFoundException;
import utp.edu.pe.apirestschool.exception.userexception.ValidateServiceException;
import utp.edu.pe.apirestschool.mapper.UsuarioMapper;
import utp.edu.pe.apirestschool.repository.UsuarioRepository;
import utp.edu.pe.apirestschool.security.JWTService;
import utp.edu.pe.apirestschool.service.UsuarioService;
import utp.edu.pe.apirestschool.validator.UsuarioValidator;


@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JWTService jwtService;
  private final UsuarioMapper usuarioMapper;

  @Override
  @Transactional(readOnly = true)
  public List<Usuario> findAll(Pageable page) {
    try {
      return usuarioRepository.findAll(page).toList();
    } catch (ValidateServiceException | NoDataFoundException e) {
      log.info(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new GeneralServiceException(e.getMessage());
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<Usuario> findByEmail(String email, Pageable page) {
    try {
      return usuarioRepository.findByEmailContaining(email, page);
    } catch (ValidateServiceException | NoDataFoundException e) {
      log.info(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new GeneralServiceException(e.getMessage());
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Usuario findById(int id) {
    try {
      return usuarioRepository
          .findById((long) id)
          .orElseThrow(() -> new NoDataFoundException("No existe el usuario con id" + id));
    } catch (ValidateServiceException | NoDataFoundException e) {
      log.info(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new GeneralServiceException(e.getMessage());
    }
  }

  @Override
  @Transactional
  public Usuario update(Usuario usuario) {
    try {
      UsuarioValidator.save(usuario);
      Usuario registroDb =
          usuarioRepository
              .findByEmail(usuario.getEmail())
              .orElseThrow(
                  () -> new NoDataFoundException("No existe el usuario para el email ingresado"));
      if (registroDb != null && registroDb.getId() != usuario.getId()) {
        throw new ValidateServiceException(
            "Ya existe un registro con el email " + usuario.getEmail());
      }
      Usuario registro =
          usuarioRepository
              .findById(usuario.getId())
              .orElseThrow(
                  () ->
                      new NoDataFoundException(
                          "No existe el registro con el id: " + usuario.getId()));
      registro.setRol(usuario.getRol());
      usuarioRepository.save(registro);
      return registro;

    } catch (ValidateServiceException | NoDataFoundException e) {
      log.info(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new GeneralServiceException(e.getMessage());
    }
  }

  @Override
  @Transactional
  public Usuario save(Usuario usuario) {
    try {
      UsuarioValidator.save(usuario);
      Optional<Usuario> reg = usuarioRepository.findByEmail(usuario.getEmail());
      if (reg.isPresent()) {
        throw new ValidateServiceException(
            "Ya existe un registro con el email :" + usuario.getEmail());
      }
      String hashPassword = passwordEncoder.encode(usuario.getPassword());
      usuario.setPassword(hashPassword);
      usuario.setActivo(true);
      Usuario registro = usuarioRepository.save(usuario);
      return registro;
    } catch (ValidateServiceException | NoDataFoundException e) {
      log.info(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new GeneralServiceException(e.getMessage());
    }
  }

  @Override
  @Transactional
  public void delete(int id) {
    try {
      Usuario registro =
          usuarioRepository
              .findById((long) id)
              .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese Id"));
      usuarioRepository.delete(registro);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new GeneralServiceException(e.getMessage());
    }
  }

  @Override
  public LoginResponseDto login(LoginRequestDto loginRequestDto) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequestDto.getEmail(), loginRequestDto.getPassword()));
      var usuario = usuarioRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();
      var jwtToken = jwtService.generateToken(usuario);
      var refreshJwtToken = jwtService.generateRefreshToken(usuario);
      return new LoginResponseDto(usuarioMapper.fromEntity(usuario), jwtToken, refreshJwtToken);
    } catch (JwtException e) {
      log.info(e.getMessage(), e);
      throw new ValidateServiceException(e.getMessage());
    } catch (Exception e) {
      log.info(e.getMessage(), e);
      throw new ValidateServiceException(e.getMessage());
    }
  }
}
