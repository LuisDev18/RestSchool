package utp.edu.pe.apirestschool.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDto {

  @NotBlank(message = "El campo idAsistencia no puede ser vacio")
  private Integer idAsistencia;

  @NotBlank(message = "El campo idClase no puede ser vacio")
  private Integer idClase;

  @NotBlank(message = "El campo idEstudiante no puede ser vacio")
  private Integer idEstudiante;

  @NotBlank(message = "El campo fechaAsistencia no puede ser vacio")
  private Date fechaAsistencia;

  private String observacion;

  @NotBlank(message = "El campo auxiliar no puede ser vacio")
  private String auxiliar;
}
