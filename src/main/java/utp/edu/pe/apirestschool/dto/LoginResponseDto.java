package utp.edu.pe.apirestschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
  private UsuarioResponseDto usuario;
  private String token;
  private String refreshToken;
}
