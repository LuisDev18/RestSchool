package utp.edu.pe.ApiRestSchool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDto {


    private Integer id;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String dni;

    private String celular;

    private String email;

    private String genero;

    private String padreApoderado;

    private String direccion;

}
