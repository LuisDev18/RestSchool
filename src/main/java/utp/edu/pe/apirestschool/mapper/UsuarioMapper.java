package utp.edu.pe.apirestschool.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.apirestschool.dto.UsuarioRequestDto;
import utp.edu.pe.apirestschool.dto.UsuarioResponseDto;
import utp.edu.pe.apirestschool.entity.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  UsuarioResponseDto fromEntity(Usuario usuario);

  Usuario fromDto(UsuarioResponseDto usuarioResponseDto);

  Usuario registro(UsuarioRequestDto usuarioRequestDto);

  List<UsuarioResponseDto> fromEntity(List<Usuario> entitys);
}
