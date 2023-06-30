package utp.edu.pe.apirestschool.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import utp.edu.pe.apirestschool.dto.UsuarioRequestDto;
import utp.edu.pe.apirestschool.dto.UsuarioResponseDto;
import utp.edu.pe.apirestschool.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  UsuarioResponseDto fromEntity(Usuario usuario);

  List<UsuarioResponseDto> fromEntity(List<Usuario> entitys);

  Usuario registro(UsuarioRequestDto usuarioRequestDto);

  Usuario fromDto(UsuarioResponseDto usuarioResponseDto);
}
