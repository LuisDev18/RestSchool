package utp.edu.pe.ApiRestSchool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.ApiRestSchool.dto.UsuarioRequestDto;
import utp.edu.pe.ApiRestSchool.dto.UsuarioResponseDto;
import utp.edu.pe.ApiRestSchool.entity.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    UsuarioResponseDto fromEntity(Usuario usuario);

    Usuario fromDto(UsuarioResponseDto usuarioResponseDto);

    Usuario registro(UsuarioRequestDto usuarioRequestDto);

    List<UsuarioResponseDto> fromEntity(List<Usuario> entitys);




}
