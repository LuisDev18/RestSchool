package utp.edu.pe.apirestschool.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import utp.edu.pe.apirestschool.dto.UsuarioRequestDto;
import utp.edu.pe.apirestschool.dto.UsuarioResponseDto;
import utp.edu.pe.apirestschool.entity.Usuario;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  UsuarioResponseDto fromEntity(Usuario usuario);
  Usuario fromDto(UsuarioResponseDto usuarioResponseDto);
  Usuario registro(UsuarioRequestDto usuarioRequestDto);
  List<UsuarioResponseDto> fromEntity(List<Usuario> entitys);
}
