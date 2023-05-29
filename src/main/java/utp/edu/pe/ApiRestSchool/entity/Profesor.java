package utp.edu.pe.ApiRestSchool.entity;

import utp.edu.pe.ApiRestSchool.entity.Profesor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String nombre;


    private String apellidoPaterno;


    private String apellidoMaterno;


    @NotBlank(message="El elemento dni no puede ser vacio")
    private String dni;


    private String celular;


    private String email;


    private String curso;
}
