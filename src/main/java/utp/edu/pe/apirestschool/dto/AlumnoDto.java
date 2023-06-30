package utp.edu.pe.apirestschool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDto {

  private Integer id;

  @NotBlank(message = "El elemento nombre no puede ser vacio")
  private String nombre;

  @NotBlank(message = "El elemento Apellido Paterno no puede ser vacio")
  private String apellidoPaterno;

  @NotBlank(message = "El elemento Apellido Materno no puede ser vacio")
  private String apellidoMaterno;

  @Pattern(regexp = "(^$|[0-9]{8})", message = "El DNI debe ser de 8 digits")
  @NotBlank(message = "El elemento dni no puede ser vacio")
  private String dni;

  @Pattern(regexp = "(^$|[0-9]{9})", message = "El celular debe ser de 9 digits")
  private String celular;

  @Email(message = "Ingresar un correo electronico valido")
  private String email;

  @NotBlank(message = "El elemento genero no puede ser vacio")
  private String genero;

  @NotBlank(message = "El elemento Padre o Apoderado no puede ser vacio")
  private String padreApoderado;

  @NotBlank(message = "El elemento direccion no puede ser vacio")
  private String direccion;
}
