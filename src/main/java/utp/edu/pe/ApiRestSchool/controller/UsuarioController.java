
package utp.edu.pe.ApiRestSchool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.ApiRestSchool.dto.LoginRequestDto;
import utp.edu.pe.ApiRestSchool.dto.LoginResponseDto;
import utp.edu.pe.ApiRestSchool.dto.UsuarioRequestDto;
import utp.edu.pe.ApiRestSchool.dto.UsuarioResponseDto;
import utp.edu.pe.ApiRestSchool.entity.Usuario;
import utp.edu.pe.ApiRestSchool.mapper.UsuarioMapper;
import utp.edu.pe.ApiRestSchool.service.UsuarioService;
import utp.edu.pe.ApiRestSchool.util.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @GetMapping()
    public ResponseEntity<List<UsuarioResponseDto>> findAll(
            @RequestParam(value="email", required=false) String email,
            @RequestParam(value="offset",required=false, defaultValue = "0") int pageNumber,
            @RequestParam(value="limit",required=false, defaultValue = "5") int pageSize){

        Pageable pagina= PageRequest.of(pageNumber, pageSize);
        List<Usuario> registros;
        if(email==null) {
            registros=usuarioService.findAll(pagina);
        }else {
            registros=usuarioService.findByEmail(email, pagina);
        }
        List<UsuarioResponseDto> registrosDTO=mapper.fromEntity(registros);

        return new WrapperResponse(true,"success",registrosDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UsuarioResponseDto> create (@RequestBody UsuarioRequestDto usuario){
        Usuario registro = usuarioService.save(mapper.registro(usuario));
        return new WrapperResponse(true,"success",mapper.fromEntity(registro)).createResponse(HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable("id") int id, @RequestBody UsuarioRequestDto usuario){
        Usuario registro=usuarioService.update(mapper.registro(usuario));
        if(registro==null) {
            return ResponseEntity.notFound().build();
        }
        return new WrapperResponse(true,"success",mapper.fromEntity(registro)).createResponse(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<UsuarioRequestDto> delete(@PathVariable("id") int id){
        usuarioService.delete(id);
        return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
    }

    @PostMapping(value="/login")
    public ResponseEntity<WrapperResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto request){
        LoginResponseDto response=usuarioService.login(request);
        return new WrapperResponse<>(true,"success",response).createResponse(HttpStatus.OK);
    }

}
