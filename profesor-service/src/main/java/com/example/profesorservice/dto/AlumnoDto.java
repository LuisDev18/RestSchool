package com.example.profesorservice.dto;

import lombok.Data;

@Data
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
